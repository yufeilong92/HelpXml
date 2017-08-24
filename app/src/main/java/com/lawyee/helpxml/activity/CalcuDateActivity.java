package com.lawyee.helpxml.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lawyee.helpxml.R;
import com.lawyee.helpxml.base.BaseActivity;
import com.lawyee.helpxml.util.DateUtil;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.OnClick;

import static com.lawyee.helpxml.R.id.toolbar;
import static com.lawyee.helpxml.util.DateUtil.calculateDate;


/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V1.0.xxxxxxxx
 * @Package com.laywee.lawyeehelper2.activity
 * @Description: 日期计算界面
 * @author: czq
 * @date: 2017/3/20 14:58
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */

public class CalcuDateActivity extends BaseActivity {
    @Bind(R.id.toolbar_title)
    TextView mToolbarTitle;
    @Bind(toolbar)
    Toolbar mToolbar;
    @Bind(R.id.rb_normal)
    RadioButton mRbNormal;
    @Bind(R.id.rb_workday)
    RadioButton mRbWorkday;
    @Bind(R.id.rg_convert)
    RadioGroup mRgConvert;
    @Bind(R.id.tv_start)
    TextView mTvStart;
    @Bind(R.id.deviler_1)
    View mDeviler1;
    @Bind(R.id.et_month_num)
    EditText mEtMonthNum;
    @Bind(R.id.ll_month)
    LinearLayout mLlMonth;
    @Bind(R.id.et_day_num)
    EditText mEtDayNum;
    @Bind(R.id.tv_end)
    TextView mTvEnd;
    private Context mContext;
    private int mPosition = 0; //0：普通计算 1：工作日计算
    Calendar mCalendar=Calendar.getInstance();
    String mTitle_name;
    @Override
    protected int setLayoutId() {
        return R.layout.activity_calcu_date;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mTitle_name=getIntent().getStringExtra("title_name");
        initToolbar(mToolbar,mToolbarTitle,mTitle_name,1);
        initView();
    }




    private void initView() {
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy/MM/dd");
        String time=format.format(date);
        mTvStart.setText(time);
        mRgConvert.check(R.id.rb_normal);
        mRgConvert.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.rb_normal:
                        mPosition=0;
                        mDeviler1.setVisibility(View.VISIBLE);
                        mLlMonth.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rb_workday:
                        mPosition=1;
                        mDeviler1.setVisibility(View.GONE);
                        mLlMonth.setVisibility(View.GONE);
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
                setEndDate();
            }
        });
        mEtMonthNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setEndDate();
            }
        });
        mEtDayNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setEndDate();

            }
        });

    }
    //设置结束日期
    private void setEndDate() {
        String endDate = DateUtil.getEndDate(mContext,getTextViewStr(mEtMonthNum), getTextViewStr(mEtDayNum), mPosition, calculateDate(getTextViewStr(mTvStart)));
        mTvEnd.setText(endDate);
    }

    @OnClick (R.id.tv_start)
    public  void OnClick(View view){
        setdate();
        showPickDialog(mCalendar.get(Calendar.YEAR),mCalendar.get(Calendar.MONTH),mCalendar.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * @Description: 得到所设定的时间
     * @param:
     * @return:
     * @throws
     */
    private void setdate() {
        mCalendar.clear();
        mCalendar.setTime(calculateDate(getTextViewStr(mTvStart)));
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
    public void showPickDialog(int mYear, int mMonth, int mDay) {

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
                mTvStart.setText(time);

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
}
