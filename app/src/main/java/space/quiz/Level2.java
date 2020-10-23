package space.quiz;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.Random;

public class Level2 extends AppCompatActivity {

    Dialog dialog;

    public int numLeft;
    public int numRight;
    Array array = new Array();
    Random random = new Random();

    public int count = 0;   //счетчик правильных ответов

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        //убрать верхнюю полосу
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //установка текста уровня
        TextView textViewOfLevel = findViewById(R.id.text_levels);
        textViewOfLevel.setText(R.string.level1);

        //код который скругляет углы картинок
        final ImageView img_left = findViewById(R.id.img_left);
        img_left.setClipToOutline(true);
        final ImageView img_right = findViewById(R.id.img_right);
        img_right.setClipToOutline(true);

        //путь к левой и правой TextView
        final TextView text_left = findViewById(R.id.text_left);
        final TextView text_right = findViewById(R.id.text_right);

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
                    Intent intent = new Intent(Level2.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
            }
        });

        //масив c текствювов на полосе, для прогресса игры
        final int[] progress = {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5,
                R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10
        };


        //заполнения рандомно картинки и подписей
        numLeft = random.nextInt(10);
        img_left.setImageResource(array.images1[numLeft]);
        text_left.setText(array.texts1[numLeft]);

        numRight = random.nextInt(10);
        while (numRight == numLeft){    //чтобы числа были разные
            numRight = random.nextInt(10);
        }
        img_right.setImageResource(array.images1[numRight]);
        text_right.setText(array.texts1[numRight]);

        //подключаем анимацию
        final Animation a = AnimationUtils.loadAnimation(Level2.this, R.anim.alpha);

        //нажатия на левую картинку
        img_left.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    img_right.setEnabled(false);    //блокируем правую картинку
                    if (numLeft > numRight){
                        img_left.setImageResource(R.drawable.img_true);
                    }else {
                        img_left.setImageResource(R.drawable.img_false);
                    }
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (numLeft > numRight) {
                        if (count < 10) count++;    //защитуем правильный ответ

                        // закрашиваем прогресс серым цветом
                        for (int i = 0; i < 10; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //опредиляем правильные ответы и закрашивем зеленым
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }

                    } else//если левая картинка меньше
                    {
                        if (count > 0) {
                            if (count == 1) {
                                count = 0;
                            } else {
                                count = count - 2;
                            }
                        }
                        // закрашиваем прогресс серым цветом
                        for (int i = 0; i < 9; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //опредиляем правильные ответы и закрашивем зеленым
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }

                    if (count == 10) {
                        //выход из уровня
                    }else   {
                        //заполнения рандомно картинки и подписей
                        numLeft = random.nextInt(10);
                        img_left.setImageResource(array.images1[numLeft]);
                        img_left.startAnimation(a);
                        text_left.setText(array.texts1[numLeft]);

                        numRight = random.nextInt(10);
                        while (numRight == numLeft) {    //чтобы числа были разные
                            numRight = random.nextInt(10);
                        }
                        img_right.setImageResource(array.images1[numRight]);
                        img_right.startAnimation(a);
                        text_right.setText(array.texts1[numRight]);

                        img_right.setEnabled(true); // включаем обратно правую картинку
                    }

                }
                return true;
            }
        });

        //нажатия на правую картинку
        img_right.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    img_left.setEnabled(false);    //блокируем левую картинку
                    if (numRight > numLeft){
                        img_right.setImageResource(R.drawable.img_true);
                    }else {
                        img_right.setImageResource(R.drawable.img_false);
                    }
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (numRight > numLeft) {
                        if (count < 10) count++;    //защитуем правильный ответ

                        // закрашиваем прогресс серым цветом
                        for (int i = 0; i < 10; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //опредиляем правильные ответы и закрашивем зеленым
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }

                    } else//если правая картинка меньше
                    {
                        if (count > 0) {
                            if (count == 1) {
                                count = 0;
                            } else {
                                count = count - 2;
                            }
                        }
                        // закрашиваем прогресс серым цветом
                        for (int i = 0; i < 9; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //опредиляем правильные ответы и закрашивем зеленым
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }

                    if (count == 10) {
                        //выход из уровня
                    }else   {
                        //заполнения рандомно картинки и подписей
                        numLeft = random.nextInt(10);
                        img_left.setImageResource(array.images1[numLeft]);
                        img_left.startAnimation(a);
                        text_left.setText(array.texts1[numLeft]);

                        numRight = random.nextInt(10);
                        while (numRight == numLeft) {    //чтобы числа были разные
                            numRight = random.nextInt(10);
                        }
                        img_right.setImageResource(array.images1[numRight]);
                        img_right.startAnimation(a);
                        text_right.setText(array.texts1[numRight]);

                        img_left.setEnabled(true); // включаем обратно правую картинку
                    }

                }
                return true;
            }
        });
    }

    public void closeDialog(View view){
        try{
            Intent intent = new Intent(Level2.this, GameLevels.class);
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
            Intent intent = new Intent(Level2.this, GameLevels.class);
            startActivity(intent);
            finish();
        }catch (Exception e){

        }
    }



    //перевірка
    public Drawable getDrawableFromAssets(String path) throws IOException {
        Drawable img = Drawable.createFromStream(getAssets().open(path), null);
        return img;
    }
}
