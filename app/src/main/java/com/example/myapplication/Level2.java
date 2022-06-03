package com.example.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

import java.util.Random;

public class Level2 extends AppCompatActivity {
    Dialog dialog;
    Dialog dialogEnd;
    public int numLeft;
    public int numRight;
    ArrayRim array = new ArrayRim();
    Random random = new Random();
    public int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);


        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level2);


        final ImageView img_left = (ImageView) findViewById(R.id.img_left);
        img_left.setClipToOutline(true);


        final ImageView img_right = (ImageView) findViewById(R.id.img_right);
        img_right.setClipToOutline(true);

        final TextView text_left = findViewById(R.id.text_left);
        final TextView text_right = findViewById(R.id.text_right);


        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.previewdialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

        TextView textdescription = (TextView)dialog.findViewById(R.id.textdescription);
        textdescription.setText(R.string.leveltwo);

        TextView button_close = (TextView) dialog.findViewById(R.id.btnclose);
        button_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Level2.this, GameLevels.class);
                startActivity(i);
                finish();
                dialog.dismiss();
            }
        });
        Button button_continue = (Button) dialog.findViewById(R.id.btncontinue);
        button_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        dialog.show();


        //____________________________________
        dialogEnd = new Dialog(this);
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogEnd.setContentView(R.layout.dialogend);
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);
        dialogEnd.setCancelable(false);

        TextView textdescriptionend = (TextView) dialogEnd.findViewById(R.id.textdescriptionEnd);
        textdescriptionend.setText(R.string.leveltwoend);

        TextView button_close1 = (TextView) dialogEnd.findViewById(R.id.btnclose);
        button_close1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Level2.this, GameLevels.class);
                startActivity(i);
                finish();
                dialogEnd.dismiss();
            }
        });
        Button button_continue1 = (Button) dialogEnd.findViewById(R.id.btncontinue);
        button_continue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Level2.this, Level3.class);
                startActivity(i);
                finish();


                dialog.dismiss();
            }
        });



        //____________________________________

        Button button_back = (Button) findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Level2.this, GameLevels.class);
                startActivity(i);
                finish();
            }
        });


        final int[] progress = {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5, R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10
        };

        final Animation a = AnimationUtils.loadAnimation(Level2.this, R.anim.alpha);


        numLeft = random.nextInt(10);
        img_left.setImageResource(array.images[numLeft]);
        text_left.setText(array.texts[numLeft]);
        numRight = random.nextInt(10);
        while (numRight == numLeft) {
            numRight = random.nextInt(10);
        }
        img_right.setImageResource(array.images[numRight]);
        text_right.setText(array.texts[numRight]);


        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    img_right.setEnabled(false);
                    if (numLeft > numRight) {
                        img_left.setImageResource(R.drawable.img_true);
                    } else {
                        img_left.setImageResource(R.drawable.img_false);
                    }
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (numLeft > numRight) {
                        if (count < 10) {
                            count += 1;
                        }

                        for (int i = 0; i < 10; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }

                    } else {
                        if (count > 0) {
                            if (count == 1) {
                                count = 0;
                            } else {
                                count -= 2;
                            }

                        }


                        for (int i = 0; i < 9; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    if (count == 10) {
                        dialogEnd.show();
                    }
                    else {
                        numLeft = random.nextInt(10);
                        img_left.setImageResource(array.images[numLeft]);
                        img_left.startAnimation(a);
                        text_left.setText(array.texts[numLeft]);
                        numRight = random.nextInt(10);
                        while (numRight == numLeft) {
                            numRight = random.nextInt(10);
                        }
                        img_right.setImageResource(array.images[numRight]);
                        img_right.startAnimation(a);
                        text_right.setText(array.texts[numRight]);
                        img_right.setEnabled(true);
                    }
                }
                return true;
            }
        });



        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    img_left.setEnabled(false);
                    if (numRight > numLeft) {
                        img_right.setImageResource(R.drawable.img_true);
                    } else {
                        img_right.setImageResource(R.drawable.img_false);
                    }
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (numRight > numLeft) {
                        if (count < 10) {
                            count += 1;
                        }

                        for (int i = 0; i < 10; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }

                    } else {
                        if (count > 0) {
                            if (count == 1) {
                                count = 0;
                            } else {
                                count -= 2;
                            }

                        }


                        for (int i = 0; i < 9; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    if (count == 10) {
                        dialogEnd.show();
                    }
                    else {
                        numLeft = random.nextInt(10);
                        img_left.setImageResource(array.images[numLeft]);
                        img_left.startAnimation(a);
                        text_left.setText(array.texts[numLeft]);
                        numRight = random.nextInt(10);
                        while (numRight == numLeft) {
                            numRight = random.nextInt(10);
                        }
                        img_right.setImageResource(array.images[numRight]);
                        img_right.startAnimation(a);
                        text_right.setText(array.texts[numRight]);
                        img_left.setEnabled(true);
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Level2.this, GameLevels.class);
        startActivity(intent);
        finish();
    }
}