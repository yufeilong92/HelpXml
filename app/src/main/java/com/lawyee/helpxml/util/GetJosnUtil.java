package com.lawyee.helpxml.util;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.lawyee.helpxml.bean.ToolNodeBean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V1.0.xxxxxxxx
 * @Title: GetJosnUtil
 * @Package com.laywee.lawyeehelper2.util
 * @Description: 本地Json解析工具类
 * @author: czq
 * @date: 2017/3/16 16:02
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: ${year} www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */

public class GetJosnUtil {
    /**
     * @Description: "获取json文件的中的数据"
     * @param: context
     * @return: List<ToolBean>
     * @throws
     */
    public static List<ToolNodeBean> getjson(Context context) {
        ArrayList<ToolNodeBean> toolNodeBeenList  =new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        AssetManager am = context.getAssets();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    am.open("toolsConfig.json")));
            String next = "";
            while (null != (next = br.readLine())) {
                sb.append(next);
            }

        } catch (Exception e) {
            e.printStackTrace();
            sb.delete(0, sb.length());
        }
        Type type = new TypeToken<ArrayList<JsonObject>>()
        {}.getType();
        ArrayList<JsonObject> jsonObjects = new Gson().fromJson(sb.toString(), type);

        ArrayList<ToolNodeBean> arrayList = new ArrayList<>();
        for (JsonObject jsonObject : jsonObjects)
        {
            arrayList.add(new Gson().fromJson(jsonObject, ToolNodeBean.class));
        }

        return arrayList;
    }
}
