package com.lawyee.helpxml.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
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

import static com.lawyee.helpxml.util.DateUtil.calculateDate;


/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V1.0.xxxxxxxx
 * @Title StartLawTimeActivity
 * @Package com.laywee.lawyeehelper2.activity
 * @Description: 公告开庭日期计算工具界面
 * @author: CZQ
 * @date: 2017/3/21 10:20
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class StartLawTimeActivity extends BaseActivity {

    @Bind(R.id.toolbar_title)
    TextView mToolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.et_notice_day)
    EditText mEtNoticeDay;
    @Bind(R.id.et_proof_day)
    EditText mEtProofDay;
    @Bind(R.id.tv_notice_period_start)
    TextView mTvNoticePeriodStart;
    @Bind(R.id.tv_notice_period_end)
    TextView mTvNoticePeriodEnd;
    @Bind(R.id.tv_reply_day)
    TextView mTvReplyDay;
    @Bind(R.id.tv_reply_start)
    TextView mTvReplyStart;
    @Bind(R.id.tv_reply_end)
    TextView mTvReplyEnd;
    @Bind(R.id.tv_proof_deadline_start)
    TextView mTvProofDeadlineStart;
    @Bind(R.id.tv_proof_deadline_end)
    TextView mTvProofDeadlineEnd;
    @Bind(R.id.tv_lawful_day)
    TextView mTvLawfulDay;
    @Bind(R.id.tv_notice)
    TextView mTvNotice;
    @Bind(R.id.tv_notice_send_day)
    TextView mTvNoticeSendDay;
    @Bind(R.id.cb_about_out)
    CheckBox mCbAboutOut;
    private Context mContext;
    private String mtitleName;
    Calendar mCalendar = Calendar.getInstance();
    private  Date mNoticeStartDate;//公告日期
    private  Date mNoticeEndDate;//公告日期+公告时间后的日期
    private int mNotice_day; //公告时间
    private int mProod_day;//举证时间
    DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
    boolean misCheck=false; //涉外是否选中
    @Override
    protected int setLayoutId() {
        return R.layout.activity_start_law_time;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mtitleName = getIntent().getStringExtra("title_name");
        initToolbar(mToolbar, mToolbarTitle, mtitleName, 1);
        initView();
    }

    private void initView() {
        Date date = new Date();
        mNoticeStartDate=date;
        mTvNotice.setText(format.format(mNoticeStartDate));
        setNomalText(misCheck);
        mEtNoticeDay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setNomalText(misCheck);
            }
        });
        mEtProofDay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setNomalText(misCheck);
            }
        });
        mCbAboutOut.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                misCheck=isChecked;
                setNomalText(misCheck);
            }
        });
    }
    /**
     * @Description:刷新所需要设置的TextView的值
     * @param:isCheck （是否被选中）
     * @return: ${return_type}
     * @throws
     */
    private void setNomalText(boolean isCheck) {
        int isProof=1;//举证时间是否有值
        if(mEtNoticeDay.getText().toString()!=null&&!mEtNoticeDay.getText().toString().equals("")){
            mNotice_day=Integer.parseInt(mEtNoticeDay.getText().toString())-1;
            if (mNotice_day < 0) {
                mNotice_day=0;
            }
        }else{
            mNotice_day=0;
        }
        if(mEtProofDay.getText().toString()!=null&&!mEtProofDay.getText().toString().equals("")){
            mProod_day=Integer.parseInt(mEtProofDay.getText().toString())-1;
            if (mProod_day < 0) {
                mProod_day=0;
            }
        }else{
            isProof=0;
            mProod_day=0;
        }
        String startTime = format.format(mNoticeStartDate);
        mNoticeEndDate= DateUtil.judgeHoliday(DateUtil.addDate(4,mNotice_day,mNoticeStartDate),mContext);
        String endTime=format.format(mNoticeEndDate);
        mTvNoticePeriodStart.setText(startTime);
        mTvNoticePeriodEnd.setText(endTime);
        mTvNoticeSendDay.setText(endTime);
        mTvReplyStart.setText(endTime);
        if(isCheck){
            mTvReplyDay.setText(R.string._30);
            mTvReplyEnd.setText(format.format(DateUtil.judgeHoliday(DateUtil.addDate(4,29,mNoticeEndDate),mContext)));
        }else{
            mTvReplyDay.setText(R.string._15);
            mTvReplyEnd.setText(format.format(DateUtil.judgeHoliday(DateUtil.addDate(4,14,mNoticeEndDate),mContext)));
        }
        Date proofDate=DateUtil.addDate(4,1,mNoticeEndDate);
        mTvProofDeadlineStart.setText(format.format(proofDate));
        if(isProof==1){
            Date proofEndDate=DateUtil.judgeHoliday(DateUtil.addDate(4,mProod_day,proofDate),mContext);
            mTvProofDeadlineEnd.setText(format.format(proofEndDate));
            mTvLawfulDay.setText(format.format(DateUtil.judgeHoliday(DateUtil.addDate(4,3,proofEndDate),mContext)));
        }
    }

    @OnClick({R.id.tv_notice})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_notice:
                setdate();
                showPickDialog(mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
                break;
        }
    }

    /**
     * @throws
     * @Description: 显示时间对话框
     * @param: mYear 年
     * @param: mMonth 月
     * @param: mDay 日
     * @param: type 点击的TextView
     * @return:
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
                Date date = temp.getTime();
                mNoticeStartDate=date;
                String time = format.format(mNoticeStartDate);
                mTvNotice.setText(time);
                setNomalText(misCheck);

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

    /**
     * @throws
     * @Description: 得到所设定的时间
     * @param:
     * @return:
     */
    private void setdate() {
        mCalendar.clear();
        mCalendar.setTime(calculateDate(getTextViewStr(mTvNotice)));
    }
}
