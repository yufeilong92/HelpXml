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
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.ContentValues.TAG;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V 1.0 xxxxxxxx
 * @Title: LawHelperAndroid
 * @Package com.laywee.lawyeehelper2.activity.fragment
 * @Description: 案件费用fragment
 * @author: YFL
 * @date: 2017/3/22 15:27
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */


public class LitigationFragment extends BaseFragment {


    @Bind(R.id.tv_Case_type)
    TextView tvCaseType;
    @Bind(R.id.tv_element_type)
    TextView tvelementtype;

    @Bind(R.id.tv_charge_case)
    TextView tvChargeCase;
    @Bind(R.id.tv_halve_case)
    TextView tvHalveCase;
    @Bind(R.id.line_Element_View)
    LinearLayout lineElementView;
    @Bind(R.id.et_Pay_Case)
    EditText etPayCase;
    @Bind(R.id.linear_Pay_Case)
    LinearLayout linearPayCase;
    private HashMap<String, ArrayList<String>> mCaseTypeMap;
    private ArrayList<String> mCaseListPopData;
    private ArrayList<String> mElementListPopData;
    private String mSelectCaseItem;
    private String mSelectElementItem;
    private String[] mCase_type;
    private String mInputData;
    private ArrayList<String> mLllegalDatas;
    private ArrayList<String> mKowledgeDatas;
    private ArrayList<String> mPliticaDatas;

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
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_litigtion, null);
        return view;
    }


    @Override
    protected void initData() {
        super.initData();
        //案件类型 有子 有—> 添加子类 没子——》不处理
        //有子—— 先获取子类集合
        mCaseTypeMap = getmCaseTypeMap();
        mCaseListPopData = getmListFirstPopData();
        //显示在控件上
        mSelectCaseItem = mCaseListPopData.get(0);
        //添加到pop显示
        //判断案件类型是否有子类
        // 是——显示子案件布局
        setTvCaseType(mSelectCaseItem);
        getInputNumber();
        // 否 不处理
        //获取案件类型  判断类型  相应计算公式
        handleCaseType(mSelectCaseItem, mSelectElementItem);

    }

    /**
     * 判断案件类型  进行相应公式计算
     *
     * @param mSelectCaseItem    父案例类型
     * @param mSelectElementItem 子案例
     */
    private void handleCaseType(String mSelectCaseItem, String mSelectElementItem) {
        //判断类型是否时有子类

        Log.d(TAG, "流程查询:==== ");
        if (!TextUtils.isEmpty(mSelectCaseItem)) {
            Log.d(TAG, "handleCaseType: " + mSelectCaseItem);
            if (!TextUtils.isEmpty(mSelectElementItem)) {
                //有子类时 选择计算公式
                Log.d(TAG, "handleCaseType: " + mSelectElementItem);
                lineElementView.setVisibility(View.VISIBLE);
                checkCaseChilderTool(mSelectCaseItem, mSelectElementItem);
            } else {
                //无子类时 案件类型选择 计算公式
                Log.d(TAG, "子类案件为空 ");
                lineElementView.setVisibility(View.GONE);
                checkCaseParentTool(mSelectCaseItem);

            }
        }


    }

    /**
     * 既有父类也有子类时选择计算公式
     *
     * @param mSelectCaseItem    父类案例
     * @param mSelectElementItem 子类案例
     */
    private void checkCaseChilderTool(String mSelectCaseItem, String mSelectElementItem) {
        if (!TextUtils.isEmpty(mSelectCaseItem) && !TextUtils.isEmpty(mSelectElementItem)) {
            if (mCase_type != null) {
                if (mSelectCaseItem.equals(mCase_type[1])) {////非法财产案例类
                    if (mLllegalDatas != null) {
                        if (mSelectElementItem.equals(mLllegalDatas.get(0))) {//离婚 涉及财产纠纷
//                            linearPayCase.setVisibility(View.VISIBLE);
                            isshowLinearLayout(linearPayCase, true);
                            if (!TextUtils.isEmpty(mInputData)) {
                                double s = new CaseUtil().divorce_property(mInputData);
                                String money = typeUtil.getStringType(s);
                                String halveMoney = new CaseUtil().halve_money(s);
                                setResultTv(money, halveMoney);
                            } else {
                                setResultTv("", "");
                            }

                        } else if (mSelectElementItem.equals(mLllegalDatas.get(1))) {//离婚不涉及财产
//                            linearPayCase.setVisibility(View.GONE);
                            isshowLinearLayout(linearPayCase, false);
                            setResultTv("200", "100");

                        } else if (mSelectElementItem.equals(mLllegalDatas.get(2))) {//侵害人格 涉及损害赔偿
                            isshowLinearLayout(linearPayCase, true);
                            if (!TextUtils.isEmpty(mInputData)) {
                                double v = new CaseUtil().name_damage_compensate(mInputData);
                                String money = typeUtil.getStringType(v);
                                String halveMoney = new CaseUtil().halve_money(v);
                                setResultTv(money, halveMoney);
                            } else {
                                setResultTv("", "");
                            }

                        } else if (mSelectElementItem.equals(mLllegalDatas.get(3))) {//侵害人格不涉及损害赔偿
                            isshowLinearLayout(linearPayCase, false);
                            setResultTv("300", "150");

                        } else if (mSelectElementItem.equals(mLllegalDatas.get(4))) {//其他非财产
                            isshowLinearLayout(linearPayCase, false);
                            setResultTv("80", "40");
                        }
                    }
                } else if (mSelectCaseItem.equals(mCase_type[2])) {//知识案例
                    if (mKowledgeDatas != null) {
                        if (mSelectElementItem.equals(mKowledgeDatas.get(0))) {
                            isshowLinearLayout(linearPayCase, false);
                            setResultTv("800", "400");

                        } else if (mSelectElementItem.equals(mKowledgeDatas.get(1))) {//有争议的
                            isshowLinearLayout(linearPayCase,true);
                            if (!TextUtils.isEmpty(mInputData)) {
                                double v = new CaseUtil().controversial_knowledge_property(mInputData);
                                String money = typeUtil.getStringType(v);
                                String halveMoney = new CaseUtil().halve_money(v);
                                setResultTv(money, halveMoney);
                            } else {
                                setResultTv("", "");
                            }
                        }
                    }

                } else if (mSelectCaseItem.equals(mCase_type[4])) {//行政案件子类
                    if (mPliticaDatas != null) {
                        if (mSelectElementItem.equals(mPliticaDatas.get(0))) {
                            isshowLinearLayout(linearPayCase, false);
                            setResultTv("100", "50");
                        } else if (mSelectElementItem.equals(mPliticaDatas.get(1))) {
                            isshowLinearLayout(linearPayCase, false);
                            setResultTv("50", "25");
                        }
                    }
                }
            }

        }
    }

    /**
     * 只有父类时选择计算公式
     *
     * @param mSelectCaseItem 子类案例
     */
    private void checkCaseParentTool(String mSelectCaseItem) {
        if (mCase_type != null) {
            if (mSelectCaseItem.equals(mCase_type[0])) {//财产案件
                isshowLinearLayout(linearPayCase, true);
                if (!TextUtils.isEmpty(mInputData)) {
                    double v = new CaseUtil().propertys_calculation(mInputData);
                    String money = typeUtil.getStringType(v);
                    String halveMoney = new CaseUtil().halve_money(v); //减半金额计算
                    setResultTv(money, halveMoney);
                } else {
                    setResultTv("", "");
                }
            } else if (mSelectCaseItem.equals(mCase_type[3])) {//劳动争议案件
                isshowLinearLayout(linearPayCase, false);
                setResultTv("10", "5");

            }
        }

    }

    /**
     * 跟新金额显示
     *
     * @param money      全额金额
     * @param halvemoney 减半金额
     */
    private void setResultTv(String money, String halvemoney) {
        tvChargeCase.setText(money);
        tvHalveCase.setText(halvemoney);
    }

    private void getInputNumber() {
        etPayCase.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mInputData = s.toString().trim();
                handleCaseType(mSelectCaseItem, mSelectElementItem);
            }
        });

    }

    /**
     * 添加到pop显示
     * //判断案件类型是否有子类
     * // 是——显示子案件布局
     *
     * @param casetype 显示在案例名称
     */
    private void setTvCaseType(String casetype) {
        if (!casetype.isEmpty() && TextUtils.isEmpty(mSelectElementItem)) {
            tvCaseType.setText(casetype);
            //首次加载判断案件类型
            Log.d(TAG, "首次加载==");
            handleCaseType(casetype, mSelectElementItem);
        }
        if (mCaseTypeMap.size() != 0) {
            mElementListPopData = mCaseTypeMap.get(casetype);
            if (mElementListPopData != null) {
                //添加子布局，把子类显示在布局上
//                lineElementView.setVisibility(View.VISIBLE);
                isshowLinearLayout(lineElementView, true);
                mSelectElementItem = mElementListPopData.get(0);
                 //同时更新ui
                tvCaseType.setText(casetype);
                tvelementtype.setText(mSelectElementItem);

                //首次加载子类案件进行判断
                Log.d(TAG, "setTvCaseType: 有子类时加载");
                handleCaseType(casetype, mSelectElementItem);
            } else {
                isshowLinearLayout(linearPayCase, false);
                mSelectElementItem = "";
                tvCaseType.setText(casetype);
                //再次加载判断案件类型
                Log.d(TAG, "在次加载");
                handleCaseType(casetype, mSelectElementItem);
            }
        }
    }

    /**
     * @param tvElement 子元素类型
     */
    public void setTvElement(String tvElement) {
        tvelementtype.setText(tvElement);
        //元素改动进行逻辑判断
        handleCaseType(mSelectCaseItem, tvElement);

    }

    /**
     * 显示在pop
     */
    private void showPopupWindow(final int i, ArrayList<String> mList, TextView view, final String selectItem) {
        final ListPopupWindow lvpop = new ListPopupWindow(mContext);
        final ListPopWindowAdpter popWindowAdpter = new ListPopWindowAdpter(mList, mContext);
        lvpop.setAdapter(popWindowAdpter);
        lvpop.setHeight(ListPopupWindow.WRAP_CONTENT);
        lvpop.setWidth(ListPopupWindow.WRAP_CONTENT);
        lvpop.setAnchorView(view);
        lvpop.setModal(true);
        lvpop.setForceIgnoreOutsideTouch(true);
        popWindowAdpter.showSelectItem(selectItem);
        lvpop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (i == 0) {
                    mSelectCaseItem = mCaseListPopData.get(position);
                    setTvCaseType(mSelectCaseItem);
                } else {
                    mSelectElementItem = mElementListPopData.get(position);
                    setTvElement(mSelectElementItem);
                }
                popWindowAdpter.isSeclectCase(true, position);
                lvpop.dismiss();
            }
        });

        lvpop.show();

    }

    @OnClick({R.id.tv_Case_type, R.id.tv_element_type, R.id.tv_charge_case, R.id.tv_halve_case})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_Case_type:
                showPopupWindow(0, mCaseListPopData, tvCaseType, mSelectCaseItem);
                break;
            case R.id.tv_element_type:
                showPopupWindow(1, mElementListPopData, tvelementtype, mSelectElementItem);
                break;
            case R.id.et_Pay_Case:
                break;
            case R.id.tv_charge_case:
                break;
            case R.id.tv_halve_case:
                break;
        }
    }

    /**
     * @return 对应案件集合
     */
    public HashMap<String, ArrayList<String>> getmCaseTypeMap() {
        //案件总览
        mCase_type = getResources().getStringArray(R.array.Case_Type);
        String[] Lllegal_Case = getResources().getStringArray(R.array.Lllegal_Case);//非法财产案例类
        mLllegalDatas = getmListPopData(Lllegal_Case);
        String[] Knowledge_Case = getResources().getStringArray(R.array.Knowledge_Case);//知识财产子类
        mKowledgeDatas = getmListPopData(Knowledge_Case);
        String[] Politicd_Case = getResources().getStringArray(R.array.Politics_Case);//行政案件子类
        mPliticaDatas = getmListPopData(Politicd_Case);
        //添加hashMap 并显示 子类 案件布局
        mCaseTypeMap = new HashMap<>();
        mCaseTypeMap.put(mCase_type[0], null);
        mCaseTypeMap.put(mCase_type[1], mLllegalDatas);
        mCaseTypeMap.put(mCase_type[2], mKowledgeDatas);
        mCaseTypeMap.put(mCase_type[3], null);
        mCaseTypeMap.put(mCase_type[4], mPliticaDatas);
        return mCaseTypeMap;
    }


    /**
     *
     * @param mdata
     * @return 案件集合
     */
    public ArrayList<String> getmListPopData(String[] mdata) {
        ArrayList<String> mListPopData = new ArrayList<>();
        for (int i = 0; i < mdata.length; i++) {
            mListPopData.add(mdata[i]);
        }
        return mListPopData;
    }

    /**
     * @return 案件集合
     */
    public ArrayList<String> getmListFirstPopData() {
        mCaseListPopData = new ArrayList<>();
        String[] Case_Type = getResources().getStringArray(R.array.Case_Type);//案件总览
        for (int i = 0; i < Case_Type.length; i++) {
            mCaseListPopData.add(Case_Type[i]);
        }
        return mCaseListPopData;
    }


    /**
     * @param layout 要显示隐藏的布局
     * @param ishow  true 显示， false 隐藏
     */
    private void isshowLinearLayout(LinearLayout layout, boolean ishow) {
        if (ishow) {//显示
            layout.setVisibility(View.VISIBLE);
        }
        if (!ishow) {//隐藏
            layout.setVisibility(View.GONE);
        }
    }
}
