package com.example.appone;

import android.annotation.SuppressLint;
import android.os.Bundle;

import java.util.Scanner;

import android.util.Pair;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private int[] num_spins_array = {R.mipmap.num0, R.mipmap.num1, R.mipmap.num2, R.mipmap.num3,
            R.mipmap.num4, R.mipmap.num5, R.mipmap.num6, R.mipmap.num7, R.mipmap.num8, R.mipmap.num9};
    private int[] spins_now = {0, 0, 0, 0};                 //массив состояния блока чисел
    private String guessing_number;                         //преполагаемое число
    private ImageView[] num_image_id = new ImageView[4];    //массив колец с числами
    private Pair pair_num = new Pair(0, 0);                 //пара Быка и Коровы
    private Game game;                  //
    private int count_press_check = -1; //счетчик ходов

    @SuppressLint("NonConstantResourceId")
    public void OnClick(View v) {
        /*
        switch (v.getId()) {
            case R.id.btn_spin1_plus:
                if (spins_now[0] == 9)
                    spins_now[0] = 0;
                else
                    spins_now[0] += 1;
                break;
            case R.id.btn_spin2_plus:
                if (spins_now[1] == 9)
                    spins_now[1] = 0;
                else
                    spins_now[1] += 1;
                break;
            case R.id.btn_spin3_plus:
                if (spins_now[2] == 9)
                    spins_now[2] = 0;
                else
                    spins_now[2] += 1;
                break;
            case R.id.btn_spin4_plus:
                if (spins_now[3] == 9)
                    spins_now[3] = 0;
                else
                    spins_now[3] += 1;
                break;
            case R.id.btn_spin1_minus:
                if (spins_now[0] == 0)
                    spins_now[0] = 9;
                else
                    spins_now[0] -= 1;
                break;
            case R.id.btn_spin2_minus:
                if (spins_now[1] == 0)
                    spins_now[1] = 9;
                else
                    spins_now[1] -= 1;
                break;
            case R.id.btn_spin3_minus:
                if (spins_now[2] == 0)
                    spins_now[2] = 9;
                else
                    spins_now[2] -= 1;
                break;
            case R.id.btn_spin4_minus:
                if (spins_now[3] == 0)
                    spins_now[3] = 9;
                else
                    spins_now[3] -= 1;
                break;

        }

         */
        Button press_check = findViewById(R.id.press_check);
        int sel_spin = 0;
        switch (v.getId()) {
            case R.id.btn_spin1_plus:   //выбор по ID кнопки
                spins_now[0] += 1;      //прокрутка кольца на одно делениеЫ
                break;
            case R.id.btn_spin2_plus:
                spins_now[1] += 1;
                sel_spin = +1;          //запоминание обновившегося кольца
                break;
            case R.id.btn_spin3_plus:
                spins_now[2] += 1;
                sel_spin = +2;
                break;
            case R.id.btn_spin4_plus:
                spins_now[3] += 1;
                sel_spin = +3;
                break;
            case R.id.btn_spin1_minus:
                spins_now[0] -= 1;
                break;
            case R.id.btn_spin2_minus:
                spins_now[1] -= 1;
                sel_spin = +1;
                break;
            case R.id.btn_spin3_minus:
                spins_now[2] -= 1;
                sel_spin = +2;
                break;
            case R.id.btn_spin4_minus:
                spins_now[3] -= 1;
                sel_spin = +3;
                break;
        }
        String spin_temp_warn = "";
        int btn_unlock = 0;
        for (int i = 0; i <= 3; i++) {
            if (spins_now[i] == 10) {       //перекрутка с 10 на 0
                spins_now[i] = 0;
            } else if (spins_now[i] == -1)
                spins_now[i] = 9;           //аналогично
            for (int j = 0; j <= 3; j++) {
                if (spins_now[i] == spins_now[j] && i != j) //проверка на наличие одинаковых символов
                    btn_unlock += 1;
            }
            spin_temp_warn += spins_now[i];
        }

        num_image_id[sel_spin].setImageResource(num_spins_array[spins_now[sel_spin]]);  //прокрутка кольца
        if (btn_unlock > 0)
            press_check.setEnabled(false); //дезактивация
        else
            press_check.setEnabled(true);  //активация кнопки CHECK
        guessing_number = spin_temp_warn;  //передача числа в Game

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //блок
        ImageView image_num_spin1 = findViewById(R.id.image_num_spin1);
        ImageView image_num_spin2 = findViewById(R.id.image_num_spin2);
        ImageView image_num_spin3 = findViewById(R.id.image_num_spin3);
        ImageView image_num_spin4 = findViewById(R.id.image_num_spin4);
        ImageButton btn_spin1_plus = findViewById(R.id.btn_spin1_plus);
        ImageButton btn_spin2_plus = findViewById(R.id.btn_spin2_plus);
        ImageButton btn_spin3_plus = findViewById(R.id.btn_spin3_plus);
        ImageButton btn_spin4_plus = findViewById(R.id.btn_spin4_plus);
        ImageButton btn_spin1_minus = findViewById(R.id.btn_spin1_minus);
        ImageButton btn_spin2_minus = findViewById(R.id.btn_spin2_minus);
        ImageButton btn_spin3_minus = findViewById(R.id.btn_spin3_minus);
        ImageButton btn_spin4_minus = findViewById(R.id.btn_spin4_minus);

        Button press_new_game = findViewById(R.id.press_new_game);
        Button press_exit = findViewById(R.id.press_exit);
        Button press_check = findViewById(R.id.press_check);
        Button press_reset = findViewById(R.id.press_reset);

        TextInputEditText out_bulls_found = findViewById(R.id.out_bulls_found);
        TextInputEditText out_cows_found = findViewById(R.id.out_cows_found);
        TextInputEditText out_steps = findViewById(R.id.out_steps);

        TextView check_out_guess = findViewById(R.id.check_out_guess);
        TextView check_out_think = findViewById(R.id.check_out_think);

        //блок ПОДГОТОВКИ
        num_image_id[0] = image_num_spin1;
        num_image_id[1] = image_num_spin2;
        num_image_id[2] = image_num_spin3;
        num_image_id[3] = image_num_spin4;

        for (int i = 0; i <= 3; i++) {
            num_image_id[i].setImageResource(num_spins_array[0]);   //установка колец на 0000
        }
        press_check.setEnabled(false);  //дезактивация кнопки CHECK на старте

        //блок ОСНОВНОЙ ИГРЫ:
        reset();
        new_game();

        //блок перезапуска игры - NEW GAME
        press_new_game.setOnClickListener(view -> MainActivity.this.new_game());

        //блок кнопки RESET
        press_reset.setOnClickListener(view -> MainActivity.this.reset());

        //блок кнопки CHECK
        press_check.setOnClickListener(view -> MainActivity.this.check());

        //блок кнопки выхода в другое ACTIVITY

        //блок выхода из игры
        press_exit.setOnClickListener(view -> MainActivity.this.finish());
    }

    private void clear_spins() {
        runOnUiThread(new Thread(() -> {
            for (int i = 0; i <= 3; i++) {
                num_image_id[i].setImageResource(num_spins_array[0]);
            }
        }));
    }
    private void reset() {
        runOnUiThread(new Thread(() -> {
            TextInputEditText out_steps = findViewById(R.id.out_steps);
            Button press_check = findViewById(R.id.press_check);
            press_check.setEnabled(false);
            clear_spins();
            out_steps.setText(String.valueOf(count_press_check));
            for (int i = 0; i <= 3; i++)
                spins_now[i] = 0;
        }));
    }
    private void new_game() {
        runOnUiThread(new Thread(() -> {
            TextInputEditText out_cows_found = findViewById(R.id.out_cows_found);
            TextInputEditText out_bulls_found = findViewById(R.id.out_bulls_found);
            game = new Game(4);
            count_press_check = 0;
            reset();
            out_bulls_found.setText("0");
            out_cows_found.setText("0");

        }));
    }
    private void check() {
        runOnUiThread(new Thread(() -> {
            TextInputEditText out_cows_found = findViewById(R.id.out_cows_found);
            TextInputEditText out_bulls_found = findViewById(R.id.out_bulls_found);
            TextInputEditText out_steps = findViewById(R.id.out_steps);
            count_press_check++;
            pair_num = this.game.checking_number(guessing_number);
            out_steps.setText(String.valueOf(count_press_check));
            out_bulls_found.setText(String.valueOf(pair_num.first));
            out_cows_found.setText(String.valueOf(pair_num.second));
            if (pair_num.first.equals(4)) {
                Toast.makeText(getBaseContext(), "CONGRATULATIONS!!!", Toast.LENGTH_LONG).show();
            }
        }));
    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

    }
}