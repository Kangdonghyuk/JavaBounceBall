
public class Main{
    public static Main I;
    private static String sceneName;

    public Main() {
        I = this;
    }

    public static void main(String[] args) {
        sceneName = "Menu";

        Information information = new Information();
        information.setScreenSize(1200, 740);
        information.setBlockSize(40);
        information.setContainerSize(1200, 700);

        SoundManager soundManager = new SoundManager();
        soundManager.I.PlaySound("src/sound/GameBGM.wav");

        new FileManager();

        setScene("Game");
        //GameManager.I.enterGame();
        //GameManager.I.gameLoop();
    }

    public static void setScene(String scene) {
        sceneName = scene;

        if(sceneName == "Game") {
            new GameManager();
            GameManager.I.enterGame();
            GameManager.I.gameLoop();
        }
    }
}