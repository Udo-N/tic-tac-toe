//Name: Udochukwu Njoku
//Student ID: 59310410
//Native Application that plays tic-tac-toe

package com.example.tictactoe;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public static int turn = 0;                            //This integer is used to keep track of whose turn it is. If it is an even number it is X's turn. If it's an odd number, it's O's turn
    public static int[][] arr = new int[3][3];             //This array is used to store the X or O values with X being 0 and O being 1
    public static int winVal = 5;                          //This integer is used to store the winning value between X and O. If it's 0, X won, if it's 1, O won. If any other value, there is no winner
    public static boolean winner = false;                  //This Boolean is only True if there is a winner
    public static String winQuote = "No winner";           //This String is what is displayed in the android app

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //This loop initializes all values of arr[][] to 5 so that there's no 0 or 1 value
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                arr[i][j] = 5;
            }
        }

        //Initializing all the buttons that can be pushed in the app
        Button top_left = findViewById(R.id.top_left);
        Button top_mid = findViewById(R.id.top_mid);
        Button top_right = findViewById(R.id.top_right);
        Button mid_left = findViewById(R.id.mid_left);
        Button middle = findViewById(R.id.mid_mid);
        Button mid_right = findViewById(R.id.mid_right);
        Button bottom_left = findViewById(R.id.bottom_left);
        Button bottom_mid = findViewById(R.id.bottom_mid);
        Button bottom_right = findViewById(R.id.bottom_right);
        Button restart = findViewById(R.id.restart);

        // When the 'restart' button is pushed, arr[][] values are reinitialized to 5, turn is reset
        // to 0, winner is reset to false, winQuote is rest to "No winner" and all the X and O
        // values being displayed in the app are cleared
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                for(int i=0; i<3; i++){
                    for(int j=0; j<3; j++){
                        arr[i][j] = 5;
                    }
                }
                turn = 0;
                winner = false;
                winQuote = "No winner";
                display(winQuote);
                top_left.setForeground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.clear));
                top_mid.setForeground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.clear));
                top_right.setForeground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.clear));
                mid_left.setForeground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.clear));
                middle.setForeground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.clear));
                mid_right.setForeground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.clear));
                bottom_left.setForeground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.clear));
                bottom_mid.setForeground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.clear));
                bottom_right.setForeground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.clear));

                //Logs are included for debugging purposes
                Log.d(getClass().getName(), Arrays.deepToString(arr));
                Log.v(getClass().getName(), ""+ winner);
            }
        });

        // For each button ranging from top_left to button_right, each button is assigned to a value
        // in arr with top_left being arr[0][0], mid_right being arr[1][2] and bottom_mid being
        // arr[2][1] for example. When the button is clicked it changes the array value to 1 or 0
        // depending on whose turn it is, and displays X if the arr value is 0 or O if it is 1
        // The code for top_left is identical to that of the other 8 buttons with the only
        // difference being the arr[][] variables used in the if statements
        top_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                // If turn is an even number, and top left is not already O, and there is no winner
                // yet:
                if (turn % 2 == 0 && arr[0][0] != 1 && !winner){
                    // If top_left is already X, do not increment turn
                    if (arr[0][0] != turn%2){turn=turn+1;}
                    arr[0][0] = 0; // Change array value to O
                    // Display X in top_left
                    view.setForeground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.x_shape));
                }
                // Else if turn is an odd number and top left is not already X, and there is no
                // winner:
                else if (turn % 2 == 1 && arr[0][0] != 0 && !winner){
                    // If top_left is already O, do not increment turn
                    if (arr[0][0] != turn%2){turn=turn+1;}
                    arr[0][0] = 1; // Change array value to 1
                    // Display O in top_left
                    view.setForeground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.o_shape));
                }
                // Check if there is a winner using the Check() function
                Check();
                // If there is a winner and it is X (winVal is 0)
                if(winner && winVal == 0){
                    winQuote = "X wins";
                    display(winQuote);          // Display 'X wins'
                }
                // Else if there is a winner and it is O (winVal is 1)
                else if(winner && winVal == 1){
                    winQuote = "O wins";
                    display(winQuote);          // Display 'O wins'
                }

                //Logs are included for debugging purposes
                Log.d(getClass().getName(), Arrays.deepToString(arr));
                Log.v(getClass().getName(), ""+ winner);
            }
        });

        top_mid.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                if (turn % 2 == 0 && arr[0][1] != 1 && !winner){
                    if (arr[0][1] != turn%2){turn=turn+1;}
                    arr[0][1] = 0;
                    view.setForeground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.x_shape));
                }
                else if (turn % 2 == 1 && arr[0][1] != 0 && !winner){
                    if (arr[0][1] != turn%2){turn=turn+1;}
                    arr[0][1] = 1;
                    view.setForeground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.o_shape));
                }
                Check();
                if(winner && winVal == 0){
                    winQuote = "X wins";
                    display(winQuote);
                }
                else if(winner && winVal == 1){
                    winQuote = "O wins";
                    display(winQuote);
                }
                Log.d(getClass().getName(), Arrays.deepToString(arr));
                Log.v(getClass().getName(), ""+ winner);
            }
        });

        top_right.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                if (turn % 2 == 0 && arr[0][2] != 1 && !winner){
                    if (arr[0][2] != turn%2){turn=turn+1;}
                    arr[0][2] = 0;
                    view.setForeground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.x_shape));
                    }
                else if (turn % 2 == 1 && arr[0][2] != 0 && !winner){
                    if (arr[0][2] != turn%2){turn=turn+1;}
                    arr[0][2] = 1;
                    view.setForeground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.o_shape));
                }
                Check();
                if(winner && winVal == 0){
                    winQuote = "X wins";
                    display(winQuote);
                }
                else if(winner && winVal == 1){
                    winQuote = "O wins";
                    display(winQuote);
                }
                Log.d(getClass().getName(), Arrays.deepToString(arr));
                Log.v(getClass().getName(), ""+ winner);
            }
        });

        mid_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if (turn % 2 == 0 && arr[1][0] != 1 && !winner){
                    if (arr[1][0] != turn%2){turn=turn+1;}
                    arr[1][0] = 0;
                    view.setForeground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.x_shape));
                }
                else if (turn % 2 == 1 && arr[1][0] != 0 && !winner){
                    if (arr[1][0] != turn%2){turn=turn+1;}
                    arr[1][0] = 1;
                    view.setForeground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.o_shape));
                }
                Check();
                if(winner && winVal == 0){
                    winQuote = "X wins";
                    display(winQuote);
                }
                else if(winner && winVal == 1){
                    winQuote = "O wins";
                    display(winQuote);
                }
                Log.d(getClass().getName(), Arrays.deepToString(arr));
                Log.v(getClass().getName(), ""+ winner);
            }
        });

        middle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                if (turn % 2 == 0 && arr[1][1] != 1 && !winner){
                    if (arr[1][1] != turn%2){turn=turn+1;}
                    arr[1][1] = 0;
                    view.setForeground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.x_shape));
                }
                else if (turn % 2 == 1 && arr[1][1] != 0 && !winner){
                    if (arr[1][1] != turn%2){turn=turn+1;}
                    arr[1][1] = 1;
                    view.setForeground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.o_shape));
                }
                Check();
                if(winner && winVal == 0){
                    winQuote = "X wins";
                    display(winQuote);
                }
                else if(winner && winVal == 1){
                    winQuote = "O wins";
                    display(winQuote);
                }
                Log.d(getClass().getName(), Arrays.deepToString(arr));
                Log.v(getClass().getName(), ""+ winner);
            }
        });

        mid_right.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                if (turn % 2 == 0 && arr[1][2] != 1 && !winner){
                    if (arr[1][2] != turn%2){turn=turn+1;}
                    arr[1][2] = 0;
                    view.setForeground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.x_shape));
                }
                else if (turn % 2 == 1 && arr[1][2] != 0 && !winner){
                    if (arr[1][2] != turn%2){turn=turn+1;}
                    arr[1][2] = 1;
                    view.setForeground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.o_shape));
                }
                Check();
                if(winner && winVal == 0){
                    winQuote = "X wins";
                    display(winQuote);
                }
                else if(winner && winVal == 1){
                    winQuote = "O wins";
                    display(winQuote);
                }
                Log.d(getClass().getName(), Arrays.deepToString(arr));
                Log.v(getClass().getName(), ""+ winner);
            }
        });

        bottom_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if (turn % 2 == 0 && arr[2][0] != 1 && !winner){
                    if (arr[2][0] != turn%2){turn=turn+1;}
                    arr[2][0] = 0;
                    view.setForeground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.x_shape));
                }
                else if (turn % 2 == 1 && arr[2][0] != 0 && !winner){
                    if (arr[2][0] != turn%2){turn=turn+1;}
                    arr[2][0] = 1;
                    view.setForeground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.o_shape));
                }
                Check();
                if(winner && winVal == 0){
                    winQuote = "X wins";
                    display(winQuote);
                }
                else if(winner && winVal == 1){
                    winQuote = "O wins";
                    display(winQuote);
                }
                Log.d(getClass().getName(), Arrays.deepToString(arr));
                Log.v(getClass().getName(), ""+ winner);
            }
        });

        bottom_mid.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                if (turn % 2 == 0 && arr[2][1] != 1 && !winner){
                    if (arr[2][1] != turn%2){turn=turn+1;}
                    arr[2][1] = 0;
                    view.setForeground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.x_shape));
                }
                else if (turn % 2 == 1 && arr[2][1] != 0 && !winner){
                    if (arr[2][1] != turn%2){turn=turn+1;}
                    arr[2][1] = 1;
                    view.setForeground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.o_shape));
                }
                Check();
                if(winner && winVal == 0){
                    winQuote = "X wins";
                    display(winQuote);
                }
                else if(winner && winVal == 1){
                    winQuote = "O wins";
                    display(winQuote);
                }
                Log.d(getClass().getName(), Arrays.deepToString(arr));
                Log.v(getClass().getName(), ""+ winner);
            }
        });

        bottom_right.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                if (turn % 2 == 0 && arr[2][2] != 1 && !winner){
                    if (arr[2][2] != turn%2){turn=turn+1;}
                    arr[2][2] = 0;
                    view.setForeground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.x_shape));
                }
                else if (turn % 2 == 1 && arr[2][2] != 0 && !winner){
                    if (arr[2][2] != turn%2){turn=turn+1;}
                    arr[2][2] = 1;
                    view.setForeground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.o_shape));
                }
                Check();
                if(winner && winVal == 0){
                    winQuote = "X wins";
                    display(winQuote);
                }
                else if(winner && winVal == 1){
                    winQuote = "O wins";
                    display(winQuote);
                }
                Log.d(getClass().getName(), Arrays.deepToString(arr));
                Log.v(getClass().getName(), ""+ winner);
            }
        });
        display(winQuote); //Display winQuote when app boots up

    }

    // This function displays whatever string is input into it, in the app near the bottom of the
    // screen
    public void display(String winQuote){
        TextView tv=findViewById(R.id.textView1);
        tv.setText(winQuote);
    }

    // This function checks for a winner when called. It changes winner to true if there is a winner
    // and changes winVal to the winning value (0 or 1)
    public void Check(){
        // If the three diagonal values from top_left to bottom_right are equal:
        if (arr[0][0] == arr[1][1] && arr[1][1] == arr[2][2] && (arr[0][0] <= 1)){
            winVal = arr[0][0];     // winVal is equal to the top_left value
            winner = true;          // winner is equal to true
        }
        // Else if the three diagonal values from top_right to bottom_left are equal:
        else if (arr[0][2] == arr[1][1] && arr[1][1] == arr[2][0] && (arr[0][2] <= 1)){
            winVal = arr[0][2];     // winVal is equal to the top_right value
            winner = true;          // winner is equal to true
        }
        else {
            for (int i = 0; i < 3; i++) {
                // If three horizontal values are equal:
                if (arr[i][0] == arr[i][1] && arr[i][1] == arr[i][2] && (arr[i][0] <= 1 || arr[i][1] <= 1 || arr[i][2] <= 1)){
                    winVal = arr[i][1]; // winVal is equal to middle value (could also be left or right value. it doesn't matter)
                    winner = true;
                    break;
                }
                // Else if three vertical values are equal:
                else if (arr[0][i] == arr[1][i] && arr[1][i] == arr[2][i] && (arr[0][i] <= 1 || arr[1][i] <= 1 || arr[2][i] <= 1)){
                    winVal = arr[1][i]; // winVal is equal to middle value
                    winner = true;
                    break;
                }
            }
        }
    }

}