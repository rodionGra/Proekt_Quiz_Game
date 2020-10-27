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
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.Random;

public class Level3 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;

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

        //установка background
        ImageView background = findViewById(R.id.background);
        background.setImageResource(R.drawable.level3_background);


        //установка текста уровня
        TextView textViewOfLevel = findViewById(R.id.text_levels);
        textViewOfLevel.setTextColor(R.color.black95);
        textViewOfLevel.setText(R.string.level3);

        //код который скругляет углы картинок
        final ImageView img_left = findViewById(R.id.img_left);
        img_left.setClipToOutline(true);
        final ImageView img_right = findViewById(R.id.img_right);
        img_right.setClipToOutline(true);

        //путь к левой и правой TextView
        final TextView text_left = findViewById(R.id.text_left);
        text_left.setTextColor(R.color.black95);
        final TextView text_right = findViewById(R.id.text_right);
        text_right.setTextColor(R.color.black95);


        //вызов диалогового окна в начале игры
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);   //убрать заголовок
        dialog.setContentView(R.layout.preview_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //прозрачный фон диалогового окна
        dialog.setCancelable(false);    //окно нельзя закрыть кнопкой "назад"

        //установка картинки в диалоговое окно
        ImageView preview_img = dialog.findViewById(R.id.preview_img);
        preview_img.setImageResource(R.drawable.preview_img_3);

        //установка фона диалогового окна
        LinearLayout dialogFon = dialog.findViewById(R.id.dialogFon);
        dialogFon.setBackgroundResource(R.drawable.preview_background3);

        //установка описания в диалоговое окно
        TextView textDescription = dialog.findViewById(R.id.text_description);
        textDescription.setText(R.string.level_three);


        Button btnClose = dialog.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent(Level3.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }
                dialog.dismiss();   //закрыть диалоговое окно
            }
        });

        Button btnContinue = dialog.findViewById(R.id.btn_continue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();


        //кнопка "назад" из уровня в меню
        Button btnBack = findViewById(R.id.button_back);
        btnBack.setBackgroundResource(R.drawable.button_stroke_black95_press_white);
        btnBack.setTextColor(R.color.black95);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Level3.this, GameLevels.class);
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
        numLeft = random.nextInt(21);
        img_left.setImageResource(array.images3[numLeft]);
        text_left.setText(array.texts3[numLeft]);

        numRight = random.nextInt(21);
        while (numRight == numLeft){    //чтобы числа были разные
            numRight = random.nextInt(21);
        }
        img_right.setImageResource(array.images3[numRight]);
        text_right.setText(array.texts3[numRight]);

        //подключаем анимацию
        final Animation a = AnimationUtils.loadAnimation(Level3.this, R.anim.alpha);

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
                        dialogEnd.show();
                    }else   {
                        //заполнения рандомно картинки и подписей
                        numLeft = random.nextInt(21);
                        img_left.setImageResource(array.images3[numLeft]);
                        img_left.startAnimation(a);
                        text_left.setText(array.texts3[numLeft]);

                        numRight = random.nextInt(21);
                        while (numRight == numLeft) {    //чтобы числа были разные
                            numRight = random.nextInt(21);
                        }
                        img_right.setImageResource(array.images3[numRight]);
                        img_right.startAnimation(a);
                        text_right.setText(array.texts3[numRight]);

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
                        dialogEnd.show();
                    }else   {
                        //заполнения рандомно картинки и подписей
                        numLeft = random.nextInt(21);
                        img_left.setImageResource(array.images3[numLeft]);
                        img_left.startAnimation(a);
                        text_left.setText(array.texts3[numLeft]);

                        numRight = random.nextInt(21);
                        while (numRight == numLeft) {    //чтобы числа были разные
                            numRight = random.nextInt(21);
                        }
                        img_right.setImageResource(array.images3[numRight]);
                        img_right.startAnimation(a);
                        text_right.setText(array.texts3[numRight]);

                        img_left.setEnabled(true); // включаем обратно правую картинку
                    }

                }
                return true;
            }
        });


        //вызов диалогового окна в конце игры
        dialogEnd = new Dialog(this);
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);   //убрать заголовок
        dialogEnd.setContentView(R.layout.end_dialog);
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //прозрачный фон диалогового окна
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);   //розтянуть на ширину экрана
        dialogEnd.setCancelable(false);    //окно нельзя закрыть кнопкой "назад"

        //установка фона диалового окна
        LinearLayout dialogFonEnd = dialogEnd.findViewById(R.id.dialogFon);
        dialogFonEnd.setBackgroundResource(R.drawable.preview_background3);

        //интересный факт
        TextView texDescriptionEnd = dialogEnd.findViewById(R.id.text_description_end);
        texDescriptionEnd.setText(R.string.level_three_end);

        Button btnCloseDialogEnd = dialogEnd.findViewById(R.id.btn_close);
        btnCloseDialogEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent(Level3.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }
                dialogEnd.dismiss();   //закрыть диалоговое окно
            }
        });

        Button btnContinueDialogEnd = dialogEnd.findViewById(R.id.btn_continue);
        btnContinueDialogEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent(Level3.this, Level4.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){

                }
                dialogEnd.dismiss();
            }
        });
        //конец кода диалового окна в конце кровня

    }

    @Override
    public void onBackPressed(){
        try{
            Intent intent = new Intent(Level3.this, GameLevels.class);
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
