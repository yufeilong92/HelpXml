package com.lawyee.helpxml.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lawyee.helpxml.R;
import com.lawyee.helpxml.base.BaseActivity;
import com.lawyee.helpxml.util.DateUtil;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.OnClick;

import static com.lawyee.helpxml.R.id.toolbar;


/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V1.0.xxxxxxxx
 * @Title: CalcuPeriodActivity
 * @Package com.laywee.awyeehelper2.activity
 * @Description: 期间计算activity
 * @author: czq
 * @date: 2017/3/17 10:40
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class CalcuPeriodActivity extends BaseActivity {

    @Bind(R.id.rb_normal)
    RadioButton mRbNormal;
    @Bind(R.id.rb_workday)
    RadioButton mRbWorkday;
    @Bind(R.id.rg_convert)
    RadioGroup mRgConvert;
    @Bind(R.id.tv_start)
    TextView mTvStart;
    @Bind(R.id.tv_end)
    TextView mTvEnd;
    @Bind(R.id.tv_value)
    TextView mTvValue;
    @Bind(R.id.toolbar_title)
    TextView mToolbarTitle;
    @Bind(toolbar)
    Toolbar mToolbar;
    private Context mContext;
    private int mposition = 0; //0：普通计算 1：工作日计算
    Calendar mCalendar=Calendar.getInstance();
    String mTitle_name;
    @Override
    protected int setLayoutId() {
        return R.layout.activity_calcu_period;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        mTitle_name=getIntent().getStringExtra("title_name");
        initToolbar(mToolbar,mToolbarTitle,mTitle_name,1);
        initView();
    }


    private void initView() {
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy/MM/dd");
        String time=format.format(date);
        mTvStart.setText(time);
        mTvEnd.setText(time);
        mRgConvert.check(R.id.rb_normal);
        mRgConvert.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.rb_normal:
                        mposition=0;
                        setTvValue();
                        break;
                    case R.id.rb_workday:
                        mposition=1;
                        setTvValue();
                        break;
                }
            }
        });
        mTvStart.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setTvValue();


            }
        });
        mTvEnd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setTvValue();

            }
        });



    }

    @OnClick({R.id.tv_start,R.id.tv_end})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_start:
                setdate(0);
                showPickDialog(mCalendar.get(Calendar.YEAR),mCalendar.get(Calendar.MONTH),mCalendar.get(Calendar.DAY_OF_MONTH),0);
                break;
            case R.id.tv_end:
                setdate(1);
                showPickDialog(mCalendar.get(Calendar.YEAR),mCalendar.get(Calendar.MONTH),mCalendar.get(Calendar.DAY_OF_MONTH),1);
                break;
        }
    }
    /**
     * @Description: 得到所设定的时间
     * @param: post  0：tvStart 1：tv_End
     * @return:
     * @throws
     */
    private void setdate(int post) {
        mCalendar.clear();
        mCalendar.setTime(calculateDate(post));
    }
    /**
     * @Description: String 转Date
     * @param: post  0：tvStart 1：tv_End
     * @return:
     * @throws
     */
    private Date calculateDate(int post) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");//小写的mm表示的是分钟
        String dstr;
        Date date =new Date();
        if(post==0){
            dstr  =mTvStart.getText().toString();
        }else{
            dstr  =mTvEnd.getText().toString();
        }
        try {
            date=sdf.parse(dstr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    /**
     * @Description: 显示时间对话框
     * @param: mYear 年
     * @param: mMonth 月
     * @param: mDay 日
     * @param: type 点击的TextView
     * @return:
     * @throws
     */
    public void showPickDialog(int mYear, int mMonth, int mDay, final int type) {

        Calendar now = Calendar.getInstance();
        now.set(mYear, mMonth, mDay);
        DatePickerDialog dialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                Calendar temp = Calendar.getInstance();
                temp.clear();
                temp.set(year, monthOfYear, dayOfMonth);
                Date date=temp.getTime();
                DateFormat format=new SimpleDateFormat("yyyy/MM/dd");
                String time=format.format(date);
                if(type==0){
                    mTvStart.setText(time);
                }else {
                    mTvEnd.setText(time);
                }

            }
        }, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));

        dialog.setMaxDate(Calendar.getInstance());
        Calendar minDate = Calendar.getInstance();
        minDate.set(1990, 1, 1);//最小日期设定

        dialog.setMinDate(minDate);
        Calendar MaxDate = Calendar.getInstance();
        MaxDate.set(2020, 12, 31);
        dialog.setMaxDate(MaxDate);//最大日期设定
        dialog.vibrate(false);
        dialog.show(getFragmentManager(), "DatePickerDialog");

    }
    //设定选择日期后的tvVaule的数值
    private void setTvValue(){
        if(mposition==0){
            mTvValue.setText(DateUtil.getDateDiff(calculateDate(0),calculateDate(1))+"");
        }else{
            mTvValue.setText(DateUtil.intervalBetweenDateWorkDays(calculateDate(0),calculateDate(1),mContext)+"");
        }
    }

}
