
public class Main{

    public static void main(String[] args) {
        Information information = new Information();
        information.setScreenSize(1200, 740);
        information.setContainerSize(1200, 700);
        information.setMaxIndex(60, 36);

        //SoundManager soundManager = new SoundManager();
        //soundManager.I.PlaySound("src/sound/GameBGM.wav");

        //Container container = new Container(
          //      information.containerWidth, information.containerHeight);

        //ContainerWriter containerWriter = new ContainerWriter(container);

        FileManager fileManager = new FileManager();
        fileManager.readStage(1);

        Ball ball = new Ball(200, 50, 8);

        BlockController blockController = new BlockController();
        blockController.setMap(fileManager.getMap());

        GameRenderer gRenderer = new GameRenderer();
        gRenderer.addRenderer(new BlockRenderer());
        gRenderer.addRenderer(new BallRenderer(ball));

        GameManager gameManager = new GameManager(ball, gRenderer);

        gameManager.gameLoop();
    }
}