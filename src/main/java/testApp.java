import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


import java.util.ArrayList;
import java.util.List;


/*
todo
2 player
battle(crit change dodge chance)
potion drop(on enemy death)
battle text;
level swap tussen levels
main menu to playeraantal select to name input
dino sprite vinden(argh)
*/

public class testApp extends GameApplication {
    public static Entity player;
    public static List<Entity> objects;
    private int width = 1280;
    private int height = 720;
    private Boolean npcCollide = false;
    public boolean levelSwap = false;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(width);
        settings.setHeight(height);
        settings.setTitle("Basic Game App");
        settings.setVersion("0.1");
    }

    @Override
    protected void initInput() {
        int moveSpeed = 2;

        if (npcCollide){
            FXGL.onKey(KeyCode.F, () -> {
                player.translateX(moveSpeed); // move right moveSpeed pixels
                if (player.isColliding(FXGL.getGameWorld().getSingleton(testTypes.NPC))){
                    System.out.println("blabla");
                }
            });
        }

        FXGL.onKey(KeyCode.D, () -> {
            player.translateX(moveSpeed); // move right moveSpeed pixels
            for (Entity object : objects) {
                if (player.isColliding(object)) {
                    player.translateX(-moveSpeed);
                }
            }
        });

        FXGL.onKey(KeyCode.A, () -> {
            player.translateX(-moveSpeed); // move left moveSpeed pixels
            for (Entity object : objects) {
                if (player.isColliding(object)) {
                    player.translateX(moveSpeed);
                }
            }
        });

        FXGL.onKey(KeyCode.W, () -> {
            player.translateY(-moveSpeed); // move up moveSpeed pixels
            for (Entity object : objects) {
                if (player.isColliding(object)) {
                    player.translateY(moveSpeed);
                }
            }
        });


        FXGL.onKey(KeyCode.S, () -> {
            player.translateY(moveSpeed); // move down moveSpeed pixels
            for (Entity object : objects) {
                if (player.isColliding(object)) {
                    player.translateY(-moveSpeed);
                }
            }
        });
    }

    private Entity monster;


    public void onUpdate(double tpf){
        if (levelSwap) {
            objects = FXGL.getGameWorld().getEntitiesByType(testTypes.FOREST,testTypes.TREEDESPAWN);
            testApp.player = FXGL.getGameWorld().getSingleton(testTypes.PLAYER);
            FXGL.getGameScene().getViewport().bindToEntity(player, width/2, height/2);
            FXGL.getGameScene().getViewport().setZoom(1.8);
            levelSwap = false;
        }
    }

    @Override
    protected void initPhysics() {
        FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(testTypes.PLAYER, testTypes.MONSTER) {

            // order of types is the same as passed into the constructor
            @Override
            protected void onCollisionBegin(Entity player, Entity monster) {
                levelSwap = true;
                FXGL.setLevelFromMap("eindlevel.tmx");
//                FXGL.getSceneService().pushSubScene(new battleScene(player, monster));
            }
        });

        FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(testTypes.PLAYER, testTypes.NPC) {

            // order of types is the same as passed into the constructor
            @Override
            protected void onCollisionBegin(Entity player, Entity npc) {
                objects.remove(FXGL.getGameWorld().getSingleton(testTypes.TREEDESPAWN));
                FXGL.getGameWorld().getSingleton(testTypes.TREEDESPAWN).removeFromWorld();
                FXGL.getGameWorld().getSingleton(testTypes.NPC).removeFromWorld();
                dialogue();

            }
        });
    }

    public void dialogue(){
        VBox content = new VBox(
                FXGL.getAssetLoader().loadTexture("heiko.png"),
                FXGL.getUIFactoryService().newText("Hello there brave adventurerer, my name Heiko. Whats your name?"),
                FXGL.getUIFactoryService().newText("Nice to meet you " + "something to get names" + "!"),
                FXGL.getUIFactoryService().newText("I need your help. All these chickens a ravaging the forest."),
                FXGL.getUIFactoryService().newText("This would be a great assesment for you, maybe I'll make you my"),
                FXGL.getUIFactoryService().newText("student but only if you can kill 5 chickens for me.")
        );

        Button btnClose = FXGL.getUIFactoryService().newButton("Press to close");
        btnClose.setPrefWidth(300);

        FXGL.getDialogService().showBox("Heiko the wizard of assesment", content, btnClose);
    }

    @Override
    protected void initGame(){
        FXGL.getGameWorld().addEntityFactory(new testFactory());
        FXGL.setLevelFromMap("level1.tmx");
        player = FXGL.getGameWorld().getSingleton(testTypes.PLAYER);
        objects = FXGL.getGameWorld().getEntitiesByType(testTypes.FOREST,testTypes.TREEDESPAWN);
        FXGL.play("intromusic.wav");
        FXGL.getGameScene().getViewport().bindToEntity(player, width/2, height/2);
        FXGL.getGameScene().getViewport().setZoom(1.8);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
