package space.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class GameLevels extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_levels);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



        //Кнопка перехода на 1 уровень
        TextView textView1 =(TextView) findViewById(R.id.textView1);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent( GameLevels.this , Level1.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }
            }
        });
    }

    //отдельная кнопка назад
    public void goBack(View view){
        try {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }catch (Exception e){

        }
    }


    @Override
    public void onBackPressed(){
        try {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }catch (Exception e) {

        }
    }
}