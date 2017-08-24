package com.lawyee.helpxml.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.lawyee.helpxml.R;

import butterknife.ButterKnife;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V1.0.xxxxxxxx
 * @Title: BaseActivity
 * @Package com.laywee.lawyeehelper2.base
 * @Description: activity的基类
 * @author: czq
 * @date: 2017/3/17 14:51
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: ${year} www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public abstract class BaseActivity extends AppCompatActivity {

    public static final String TITLE_NAME="title_name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("BaseActivity", "onCreate");
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);// 防止系统自带actionbar与toolbar重叠
        setContentView(setLayoutId());// 让子类去实现绑定布局
        ButterKnife.bind(this);
        ViewGroup viewGroup = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);// 获取根布局
        if (viewGroup != null) {
            View view = viewGroup.getChildAt(0);
            if (view != null) {
                view.setFitsSystemWindows(true);// 设置透明状态栏时需要
            }
        }
    }

    protected abstract int setLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    /**
     * @Description: activity 跳转
     * @param:mContext
     *  @param:classs
     *  @param:str（title）
     * @throws
     */
    public void  acToActivity(Context mContext,Class classs,String str){
        Intent intent =new Intent(mContext,classs);
        intent.putExtra(TITLE_NAME,str);
        mContext.startActivity(intent);
    }
    public String getTextViewStr(TextView textView){
            return  textView.getText().toString();
    }

    protected void initToolbar(Toolbar toolbar, TextView textView,String str,int type) {

        if (toolbar == null) {
            return;
        }
        toolbar.setTitle("");//设置标题
        setSupportActionBar(toolbar);
        textView.setText(str);
        if(type==1) {
            toolbar.setNavigationIcon(R.drawable.btn_return);//或者在布局中 app:navigationIcon="?attr/homeAsUpIndicator"
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

    }
}
