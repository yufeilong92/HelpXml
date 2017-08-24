package com.lawyee.helpxml.activity.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.lawyee.helpxml.R;
import com.lawyee.helpxml.activity.PraticalToolActivity;
import com.lawyee.helpxml.base.BaseFragment;

import butterknife.Bind;
import butterknife.OnClick;



/**
 *
 * @version V1.0.xxxxxxxx
 * @Package com.laywee.lawyeehelper2.fragment
 * @Description: 首页activity
 * @author: czq
 * @date: 2017/3/17 09:50
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */

public class MainFragment extends BaseFragment {

    @Bind(R.id.iv_quike_check)
    ImageView mIvQuikeCheck;
    @Bind(R.id.iv_kl_check)
    ImageView mIvKlCheck;
    @Bind(R.id.iv_practice_skill)
    ImageView mIvPracticeSkill;
    @Bind(R.id.iv_practice_tool)
    ImageView mIvPracticeTool;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_mainpager, null);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getActivity();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
    @OnClick (R.id.iv_practice_tool)
    public  void  onClick(View view){
        switch (view.getId()){
            case R.id.iv_practice_tool:
                FragToActivity(PraticalToolActivity.class);
                break;
            default:
                break;
        }
    }
}
