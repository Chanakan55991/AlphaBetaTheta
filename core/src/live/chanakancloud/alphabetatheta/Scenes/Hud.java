package live.chanakancloud.alphabetatheta.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import live.chanakancloud.alphabetatheta.AlphaBetaTheta;

public class Hud implements Disposable {
    public Stage stage;

    private Label countdownLabel;
    private static Label scoreLabel;
    private Label timeLabel;
    private Label levelLabel;
    private float timeCount;
    private Integer worldTimer;
    private static Integer score;
    private Label worldLabel;
    private Label characterLabel;

    public Hud(SpriteBatch sb) {
        worldTimer = 300;
        score = 0;

        Viewport viewport = new FitViewport(AlphaBetaTheta.V_WIDTH, AlphaBetaTheta.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);
        Table table = new Table();
        table.top();
        table.right();
        table.setFillParent(true);

        countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Image image = new Image(new Texture("gems.png"));


        scoreLabel = new Label(String.format("%d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        //table.add(characterLabel).expandX().padTop(10);
        //table.add(worldLabel).expandX().padTop(10);
        //table.add(timeLabel).expandX().padTop(10);
        //table.row();
        table.add(image).expandX().padLeft(370);
        table.add(scoreLabel).expandX().padRight(3);
        //table.add(levelLabel).expandX();
        //table.add(countdownLabel).expandX();

        stage.addActor(table);
    }

    public void update(float delta)
    {
        timeCount += delta;
        if(timeCount >= 1)
        {
            worldTimer--;
            countdownLabel.setText(String.format("%03d", worldTimer));
            timeCount = 0;
        }
    }

    public static void addScore(int value)
    {
        score += value;
        scoreLabel.setText(String.format("%d", score));
    }

    public static Integer getScore()
    {
        return score;
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
