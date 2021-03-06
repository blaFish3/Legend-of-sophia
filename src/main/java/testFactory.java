import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.sun.javafx.scene.text.TextLayout;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class testFactory extends Component implements EntityFactory {

//
//    @Spawns("obstacle")
//    public Entity newObstacle(SpawnData data){
//        return FXGL.entityBuilder(data)
//                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"),data.<Integer>get("height"))))
//                .type(testTypes.OBSTACLE)
//                .build();
//    }
    public static String playerOneName;
    public static String playerTwoName;

    @Spawns("obstacle")
    public Entity newForest(SpawnData data){
        return FXGL.entityBuilder(data)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"),data.<Integer>get("height"))))
                .type(testTypes.FOREST)
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("npc")
    public Entity newNpc(SpawnData data){
        return FXGL.entityBuilder(data)
                .view("heiko.png")
                .bbox(new HitBox(BoundingShape.box(30,30)))
                .type(testTypes.NPC)
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("stoneDespawn")
    public Entity newStoneDespawn(SpawnData data){
        return FXGL.entityBuilder(data)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"),data.<Integer>get("height"))))
                .type(testTypes.STONEDESPAWN)
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("eindbaas")
    public Entity newEindbaas(SpawnData data){
        return FXGL.entityBuilder(data)
                .viewWithBBox("heiko.png")
                .type(testTypes.EINDBAAS)
                .with(new CollidableComponent(true))
                .with(new monsterComponent("heiko",8, 30))
                .with(new HealthIntComponent(30))
                .build();
    }

    @Spawns("player")
    public Entity newplayer(SpawnData data){
        return FXGL.entityBuilder(data)
                .view("link.png")
                .bbox(new HitBox(BoundingShape.box(30,30)))
                .type(testTypes.PLAYER)
                .with((new CollidableComponent(true)))
                .with(new HealthIntComponent(20))
                .with(new playerComponent(playerOneName,1, 3, 0))
                .build();
    }

    @Spawns("player2")
    public Entity newplayer2(SpawnData data){
        if (testApp.twoPlayers) {
            return FXGL.entityBuilder(data)
                    .view("link.png")
                    .bbox(new HitBox(BoundingShape.box(30, 30)))
                    .type(testTypes.PLAYERTWO)
                    .with((new CollidableComponent(true)))
                    .with(new HealthIntComponent(20))
                    .with(new playerComponent(playerTwoName,2,3, 0))
                    .build();
        }
        return FXGL.entityBuilder(data)
                .build();
    }


    @Spawns("pathBlock")
    public Entity newpathBlock(SpawnData data){
        return FXGL.entityBuilder(data)
                .viewWithBBox("tree.png")
                .type(testTypes.TREEDESPAWN)
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("bug")
    public Entity newMonster(SpawnData data){
        return FXGL.entityBuilder(data)
                .viewWithBBox("monster.png")
                .type(testTypes.MONSTER)
                .with(new CollidableComponent(true))
                .with(new monsterComponent("bug",4,15))
                .with(new HealthIntComponent(15))
                .build();
    }

    @Spawns("chicken")
    public Entity newchicken(SpawnData data){
        return FXGL.entityBuilder(data)
                .viewWithBBox("chicken.png")
                .type(testTypes.MONSTER)
                .with(new CollidableComponent(true))
                .with(new monsterComponent("chicken",2,10))
                .with(new HealthIntComponent(10))
                .build();
    }
    @Spawns("dino")
    public Entity newDino(SpawnData data){
        return FXGL.entityBuilder(data)
                .viewWithBBox("dino.png")
                .type(testTypes.MONSTER)
                .with(new CollidableComponent(true))
                .with(new monsterComponent("dino",4,19))
                .with(new HealthIntComponent(19))
                .build();
    }
}
