package com.example.shoppingcart.shoppingcart;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends Activity {
    private ExpandableListView shoppingCartListView;
    private String json = "{\n" +
            "    \"shoppingCarts\": [\n" +
            "        {\n" +
            "            \"parentId\": \"1\",\n" +
            "            \"parentName\": \"商家1\",\n" +
            "            \"parentPrice\": \"3.00\",\n" +
            "            \"goodsInShoppingCarts\": [\n" +
            "                {\n" +
            "                    \"childId\": \"11\",\n" +
            "                    \"childName\": \"年货\",\n" +
            "                    \"childPrice\": \"1.00\",\n" +
            "                    \"childNum\": \"1\",\n" +
            "                    \"childPic\": \"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488026174238&di=4ded50ff446bd8ec82e05493ca9b300f&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20140903%2FImg404032892.jpg\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"childId\": \"12\",\n" +
            "                    \"childName\": \"大礼包\",\n" +
            "                    \"childPrice\": \"2.00\",\n" +
            "                    \"childNum\": \"2\",\n" +
            "                    \"childPic\": \"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488026174238&di=4ded50ff446bd8ec82e05493ca9b300f&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20140903%2FImg404032892.jpg\"\n" +
            "                }\n" +
            "            ]\n" +
            "        },\n" +
            "        {\n" +
            "            \"parentId\": \"2\",\n" +
            "            \"parentName\": \"商家2\",\n" +
            "            \"parentPrice\": \"4.00\",\n" +
            "            \"goodsInShoppingCarts\": [\n" +
            "                {\n" +
            "                    \"childId\": \"21\",\n" +
            "                    \"childName\": \"苹果\",\n" +
            "                    \"childPrice\": \"2.00\",\n" +
            "                    \"childNum\": \"1\",\n" +
            "                    \"childPic\": \"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488026174238&di=4ded50ff446bd8ec82e05493ca9b300f&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20140903%2FImg404032892.jpg\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"childId\": \"22\",\n" +
            "                    \"childName\": \"香蕉\",\n" +
            "                    \"childPrice\": \"2.00\",\n" +
            "                    \"childNum\": \"2\",\n" +
            "                    \"childPic\": \"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1488026174238&di=4ded50ff446bd8ec82e05493ca9b300f&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20140903%2FImg404032892.jpg\"\n" +
            "                }\n" +
            "            ]\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shoppingCartListView = (ExpandableListView) findViewById(R.id.ex_shopping_cart);
        shoppingCartListView.setGroupIndicator(null);
        DataEntity dataEntity = new Gson().fromJson(json, DataEntity.class);
        List<DataEntity.ShoppingCartEntity> list = dataEntity.getShoppingCarts();
        ShoppingCartAdapter expandAdapter = new ShoppingCartAdapter(this, list);
        shoppingCartListView.setAdapter(expandAdapter);
//        //        将子项全部展开
        for (int i = 0; i < list.size(); i++) {
            shoppingCartListView.expandGroup(i);
        }
//        这是parent不能点击
        shoppingCartListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
    }
}
