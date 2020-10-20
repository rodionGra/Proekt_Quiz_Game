package space.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class Level1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        //код который скругляет углы картинок
        final ImageView img_left = findViewById(R.id.img_left);
        img_left.setClipToOutline(true);
        final ImageView img_right = findViewById(R.id.img_right);
        img_right.setClipToOutline(true);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}