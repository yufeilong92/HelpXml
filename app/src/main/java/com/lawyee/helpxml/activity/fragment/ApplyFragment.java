package com.lawyee.helpxml.activity.fragment;

import android.os.Bundle;
import android.support.v7.widget.ListPopupWindow;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lawyee.helpxml.R;
import com.lawyee.helpxml.adpater.ListPopWindowAdpter;
import com.lawyee.helpxml.base.BaseFragment;
import com.lawyee.helpxml.util.CaseUtil;
import com.lawyee.helpxml.util.typeUtil;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.ContentValues.TAG;
import static com.lawyee.helpxml.R.id.et_Pay_Apply;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V 1.0 xxxxxxxx
 * @Title: LawHelperAndroid
 * @Package com.laywee.lawyeehelper2.activity.fragment
 * @Description: 申请给计算
 * @author: YFL
 * @date: 2017/3/22 15:28
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */


public class ApplyFragment extends BaseFragment {

    @Bind(R.id.tv_Case_Apply)
    TextView tvCaseApply;
    @Bind(et_Pay_Apply)
    EditText etPayApply;
    @Bind(R.id.line_Pay_Layout)
    LinearLayout linePayLayout;
    @Bind(R.id.tv_charge_Apply)
    TextView tvChargeApply;
    @Bind(R.id.tv_halve_Apply)
    TextView tvHalveApply;
    private ArrayList<String> mfee_applyType;
    private String mSelectCaseApply;
    private String mInputData;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    protected void initData() {
        super.initData();
        //获取类型集合
        mfee_applyType = getmCaseTypeMap();
        //获取第一类型
        mSelectCaseApply = mfee_applyType.get(0);
        tvCaseApply.setText(mSelectCaseApply);
        //键盘输入
        getInputNumber();
        //判断类型
        handleCaseType(mSelectCaseApply);

    }

    private void getInputNumber() {
        etPayApply.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mInputData = s.toString().trim();
                handleCaseType(mSelectCaseApply);
            }
        });
    }

    /**
     * c处理案件类型
     *
     * @param selectCaseApply
     */
    private void handleCaseType(String selectCaseApply) {
        Log.d(TAG, "handleCaseType: " + selectCaseApply);

        if (!TextUtils.isEmpty(selectCaseApply)) {
            if (selectCaseApply.equals(mfee_applyType.get(0))) {//执行费用
                isshowLinearLayout(linePayLayout,true);
                if (!TextUtils.isEmpty(mInputData)) {
                    double v = new CaseUtil().implement_apply_money(mInputData);
                    String money = typeUtil.getStringType(v);
                    String halveMoney = new CaseUtil().halve_money(v);
                    setResult(money, halveMoney);
                } else {
                    setResult("", "");
                }
            } else if (selectCaseApply.equals(mfee_applyType.get(1))) {//诉讼保全申请
                isshowLinearLayout(linePayLayout,true);
                if (!TextUtils.isEmpty(mInputData)) {
                    double v = new CaseUtil().patent_application_fee(mInputData);
                    String money = typeUtil.getStringType(v);
                    String halveMoney = new CaseUtil().halve_money(v);
                    setResult(money, halveMoney);
                } else {
                    setResult("", "");
                }
            } else if (selectCaseApply.equals(mfee_applyType.get(2))) {//申请支付令
                isshowLinearLayout(linePayLayout,true);
                if (!TextUtils.isEmpty(mInputData)) {
                    double v = new CaseUtil().apply_money(mInputData);
                    String money = typeUtil.getStringType(v);
                    String halveMoney = new CaseUtil().halve_money(v);
                    setResult(money, halveMoney);
                } else {
                    setResult("", "");
                }

            } else if (selectCaseApply.equals(mfee_applyType.get(3))) {//申请公告催告
//                isshowLinearLayout(linePayLayout,false);
                linePayLayout.setVisibility(View.GONE);
                setResult("100", "50");
            } else if (selectCaseApply.equals(mfee_applyType.get(4))) {//撤销仲裁
//                isshowLinearLayout(linePayLayout,false);
                linePayLayout.setVisibility(View.GONE);
                setResult("400", "200");

            } else if (selectCaseApply.equals(mfee_applyType.get(5))) {//破产案件
                isshowLinearLayout(linePayLayout,true);
                if (!TextUtils.isEmpty(mInputData)) {
                    double v = new CaseUtil().bankruptcy_case(mInputData);
                    String money = typeUtil.getStringType(v);
                    String halveMoney = new CaseUtil().halve_money(v);
                    setResult(money, halveMoney);
                } else {
                    setResult("", "");
                }

            }
        }

    }

    /**
     * 显示结果
     *
     * @param money
     * @param haverlmoney
     */
    private void setResult(String money, String haverlmoney) {
        tvChargeApply.setText(money);
        tvHalveApply.setText(haverlmoney);
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_apply, null);
        return view;
    }

    @OnClick({R.id.tv_Case_Apply, et_Pay_Apply, R.id.tv_charge_Apply, R.id.tv_halve_Apply})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_Case_Apply:
                showPopupWindow(mfee_applyType, tvCaseApply, mSelectCaseApply);
                break;
            case et_Pay_Apply:
                break;
            case R.id.tv_charge_Apply:
                break;
            case R.id.tv_halve_Apply:
                break;
        }
    }

    /**
     * @return 对应案件集合
     */
    public ArrayList<String> getmCaseTypeMap() {
        //案件总览
        ArrayList<String> mfee_Apply = new ArrayList<>();
        String[] fee_apply = getResources().getStringArray(R.array.implement_fee_Type);
        for (int i = 0; i < fee_apply.length; i++) {
            mfee_Apply.add(fee_apply[i]);
        }
        //添加hashMap 并显示 子类 案件布局
        return mfee_Apply;
    }

    /**
     * 显示在pop
     */
    private void showPopupWindow(ArrayList<String> mList, TextView view, final String selectItem) {
        final ListPopupWindow lvpop = new ListPopupWindow(mContext);
        final ListPopWindowAdpter PopWindowAdpter = new ListPopWindowAdpter(mList, mContext);
        lvpop.setAdapter(PopWindowAdpter);
        lvpop.setHeight(ListPopupWindow.WRAP_CONTENT);
        lvpop.setWidth(ListPopupWindow.WRAP_CONTENT);
        lvpop.setAnchorView(view);
        lvpop.setModal(true);
        lvpop.setForceIgnoreOutsideTouch(true);
        PopWindowAdpter.showSelectItem(selectItem);
        lvpop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSelectCaseApply = mfee_applyType.get(position);
                setTvCaseType(mSelectCaseApply);
                PopWindowAdpter.isSeclectCase(true, position);
                lvpop.dismiss();
            }
        });

        lvpop.show();

    }

    private void setTvCaseType(String selectCaseItem) {
        if (!TextUtils.isEmpty(selectCaseItem)) {
            //判断
            tvCaseApply.setText(selectCaseItem);
            handleCaseType(selectCaseItem);
        }
    }
    /**
     * @param layout 要显示隐藏的布局
     * @param ishow  true 显示， false 隐藏
     */
    private void isshowLinearLayout(LinearLayout layout, boolean ishow) {
        if (ishow){
            layout.setVisibility(View.VISIBLE);
        }else if (!ishow){
            layout.setVisibility(View.GONE);
        }
//       layout.setVisibility(ishow==true?View.VISIBLE:View.GONE);
    }
}
