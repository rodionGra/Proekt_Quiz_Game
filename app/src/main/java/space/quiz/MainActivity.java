package space.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private long backPressedTime;
    private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button buttonStart = findViewById(R.id.button_start);
    }

    public void goToLevels(View view){
        try {
            Intent intent = new Intent(this, GameLevels.class);
            startActivity(intent);
            finish();
        }catch (Exception e){

        }
    }

    // системная кнопка назад
    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 >= System.currentTimeMillis()){
            super.onBackPressed();
            backToast.cancel();
        }else{
            backToast = Toast.makeText(getBaseContext(),"Нажмите еще раз, чтобы выйти", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }

}