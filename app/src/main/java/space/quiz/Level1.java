package space.quiz;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Level1 extends AppCompatActivity {

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        //установка текста уровня
        TextView textViewOfLevel = findViewById(R.id.text_levels);
        textViewOfLevel.setText(R.string.level1);

        //код который скругляет углы картинок
        final ImageView img_left = findViewById(R.id.img_left);
        img_left.setClipToOutline(true);
        final ImageView img_right = findViewById(R.id.img_right);
        img_right.setClipToOutline(true);

        //убрать верхнюю полосу
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //вызов диалогового окна в начале игры
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);   //убрать заголовок
        dialog.setContentView(R.layout.preview_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //прозрачный фон диалогового окна
        dialog.setCancelable(false);    //окно нельзя закрыть кнопкой "назад"
        dialog.show();


        //кнопка назад из уровня в меню
        Button btnBack = findViewById(R.id.button_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Level1.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
            }
        });

    }

    public void closeDialog(View view){
        try{
            Intent intent = new Intent(Level1.this, GameLevels.class);
            startActivity(intent);
            finish();
        }catch (Exception e){

        }
        dialog.dismiss();   //закрыть диалоговое окно
    }

    public void continueGame(View view){
        dialog.dismiss(); //закрыть диалоговое окно
    }

    @Override
    public void onBackPressed(){
        try{
            Intent intent = new Intent(Level1.this, GameLevels.class);
            startActivity(intent);
            finish();
        }catch (Exception e){

        }
    }
}




/*btnClose.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        try{
        //Intent intent = new Intent(Level1.this, GameLevels.class);
        //startActivity(intent);
        //finish();
        }catch (Exception e){

        }
        //dialog.dismiss();   //закрыть диалоговое окно
        }
        });*/
