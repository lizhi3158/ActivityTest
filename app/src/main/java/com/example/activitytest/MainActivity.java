package com.example.activitytest;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dd.morphingbutton.MorphingButton;

public class MainActivity extends BaseActivity {

    private int mMorphCounter1 = 1;

    private int mMorphCounter2 = 1;

    public static void startThisActivity(@NonNull Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        final MorphingButton btnMorph1 = (MorphingButton) findViewById(R.id.button_1);
        btnMorph1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示Intent用法
                //Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                //隐式Intent用法
//                Intent intent = new Intent("com.example.activitytest.ACTION_START");
//                intent.addCategory("com.example.activitytest.MY_CATEGORY");
                //用隐式Intent来打开网页
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("http://www.baidu.com"));
//                startActivity(intent);
//                Toast.makeText(MainActivity.this,
//                        "You clicked Button", Toast.LENGTH_SHORT).show();
                //销毁活动
                //finish();
                //向下一个活动传递数据
//                String data = "Hello SecondActivity";
//                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//                intent.putExtra("extra_data", data);
//                startActivity(intent);
                //返回数据给上一个活动
                onMorphButton1Clicked(btnMorph1);
//                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//                startActivityForResult(intent, 1);
            }
        });

        final MorphingButton btnMorph2 = (MorphingButton) findViewById(R.id.button_4);
        btnMorph2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMorphButton2Clicked(btnMorph2);
//                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//                startActivityForResult(intent, 1);
            }
        });

        morphToSquare(btnMorph1, 0);
        morphToFailure(btnMorph2, 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(this, "You clicked Add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "You clicked Remove", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returnedData = data.getStringExtra("data_return");
                    Log.d("MainActivity", returnedData);
                }
                break;
            default:
        }
    }

    private void onMorphButton1Clicked(final MorphingButton btnMorph) {
        if (mMorphCounter1 == 0) {
            mMorphCounter1++;
            morphToSquare(btnMorph, integer(R.integer.mb_animation));
        } else if (mMorphCounter1 == 1) {
            mMorphCounter1 = 0;
            morphToSuccess(btnMorph);
        }
    }

    private void onMorphButton2Clicked(final MorphingButton btnMorph) {
        if (mMorphCounter2 == 0) {
            mMorphCounter2++;
            morphToFailure(btnMorph,  integer(R.integer.mb_animation));
        } else if (mMorphCounter2 == 1) {
            mMorphCounter2 = 0;
            morphToSquare(btnMorph, integer(R.integer.mb_animation));
        }
    }

    private void morphToSquare(final MorphingButton btnMorph, int duration) {
        MorphingButton.Params square = MorphingButton.Params.create()
                .duration(duration)
                .cornerRadius(dimen(R.dimen.mb_corner_radius_2))
                .width(dimen(R.dimen.mb_width_200))
                .height(dimen(R.dimen.mb_height_56))
                .color(color(R.color.mb_blue))
                .colorPressed(color(R.color.mb_blue_dark))
                .text(getString(R.string.mb_button));
        btnMorph.morph(square);
    }

    private void morphToSuccess(final MorphingButton btnMorph) {
        MorphingButton.Params circle = MorphingButton.Params.create()
                .duration(integer(R.integer.mb_animation))
                .cornerRadius(dimen(R.dimen.mb_height_56))
                .width(dimen(R.dimen.mb_height_56))
                .height(dimen(R.dimen.mb_height_56))
                .color(color(R.color.mb_green))
                .colorPressed(color(R.color.mb_green_dark))
                .icon(R.drawable.ic_done);
        btnMorph.morph(circle);
    }

    private void morphToFailure(final MorphingButton btnMorph, int duration) {
        MorphingButton.Params circle = MorphingButton.Params.create()
                .duration(duration)
                .cornerRadius(dimen(R.dimen.mb_height_56))
                .width(dimen(R.dimen.mb_height_56))
                .height(dimen(R.dimen.mb_height_56))
                .color(color(R.color.mb_red))
                .colorPressed(color(R.color.mb_red_dark))
                .icon(R.drawable.ic_lock);
        btnMorph.morph(circle);
    }
}
