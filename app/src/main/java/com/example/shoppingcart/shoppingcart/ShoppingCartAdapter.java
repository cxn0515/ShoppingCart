package com.example.shoppingcart.shoppingcart;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cxn on 2017/2/25.
 */

public class ShoppingCartAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<DataEntity.ShoppingCartEntity> shoppingCarts;

    public ShoppingCartAdapter(Context context, List<DataEntity.ShoppingCartEntity> shoppingCarts) {
        this.context = context;
        this.shoppingCarts = shoppingCarts;
    }

    @Override
    public int getGroupCount() {
        return shoppingCarts == null ? 0 : shoppingCarts.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return shoppingCarts.get(groupPosition) == null ? 0 : shoppingCarts.get(groupPosition).getGoodsInShoppingCarts().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return shoppingCarts.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return shoppingCarts.get(groupPosition).getGoodsInShoppingCarts().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final GroupHolder groupHolder;
        if (convertView == null) {
            groupHolder = new GroupHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.shopping_cart_item, parent, false);
            groupHolder.shoppingCartParentCheckBox = (CheckBox) convertView.findViewById(R.id.cb_shopping_cart_parent);
            groupHolder.merchantNameTextView = (TextView) convertView.findViewById(R.id.tv_merchant_name);
            groupHolder.editView = (TextView) convertView.findViewById(R.id.tv_edit);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) convertView.getTag();
        }
        final DataEntity.ShoppingCartEntity shoppingCartEntity = shoppingCarts.get(groupPosition);
        groupHolder.shoppingCartParentCheckBox.setClickable(true);
        groupHolder.shoppingCartParentCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<DataEntity.ShoppingCartEntity.GoodsInShoppingCartEntity> list = shoppingCartEntity.getGoodsInShoppingCarts();
                boolean b = shoppingCartEntity.isCheck();
                shoppingCartEntity.setCheck(!b);
                for (DataEntity.ShoppingCartEntity.GoodsInShoppingCartEntity g : list) {
                    g.setIsCheck(!b);
                }
                notifyDataSetChanged();
            }
        });
        groupHolder.shoppingCartParentCheckBox.setChecked(shoppingCartEntity.isCheck());
        final boolean isEdit = shoppingCartEntity.isEdit();
        if (isEdit) {
            groupHolder.editView.setText("完成");
        } else {
            groupHolder.editView.setText("编辑");
        }
        groupHolder.editView.setClickable(true);
        groupHolder.editView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoppingCartEntity.setEdit(!isEdit);
                notifyDataSetChanged();
            }
        });

        groupHolder.merchantNameTextView.setText(shoppingCartEntity.getParentName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildHolder childHolder;
        if (convertView == null) {
            childHolder = new ChildHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_cart_child_item, parent, false);
            childHolder.editView = convertView.findViewById(R.id.ll_shopping_cart_edit);
            childHolder.shoppingCartChildCheckBox = (CheckBox) convertView.findViewById(R.id.cb_shopping_cart_child);
            childHolder.addTextView = convertView.findViewById(R.id.tv_shopping_cart_add);
            childHolder.subTextView = convertView.findViewById(R.id.tv_shopping_cart_sub);
            childHolder.shoppingCartCountEditText = (EditText) convertView.findViewById(R.id.et_shopping_cart_child_count);
            childHolder.shoppingCartChildTitle = (TextView) convertView.findViewById(R.id.tv_child_item_goods_title);
            childHolder.shoppingCartUnitPrice = (TextView) convertView.findViewById(R.id.tv_shopping_cart_unit_price);
            childHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_shopping_cart);
            childHolder.deleteView = convertView.findViewById(R.id.tv_shopping_cart_delete_edit);
            childHolder.editAddTextView = convertView.findViewById(R.id.iv_shopping_cart_add_edit);
            childHolder.editSubTextView = convertView.findViewById(R.id.iv_shopping_cart_sub_edit);
            childHolder.editShoppingCartCountEditText = (EditText) convertView.findViewById(R.id.et_shopping_cart_child_count_edit);
            childHolder.priceAndConfirView = convertView.findViewById(R.id.ll_shopping_cart_child_item_price);
            childHolder.shoppingCartPriceTextView = (TextView) convertView.findViewById(R.id.tv_shopping_cart_price);
            childHolder.confirmTextView = (TextView) convertView.findViewById(R.id.tv_confirm);
            childHolder.numberLayout = convertView.findViewById(R.id.tv_child_item_goods_number);
            childHolder.editAddSubView = convertView.findViewById(R.id.ll_edit_add_sub);
            convertView.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) convertView.getTag();
        }
        final DataEntity.ShoppingCartEntity shoppingCartEntity = shoppingCarts.get(groupPosition);
        final List<DataEntity.ShoppingCartEntity.GoodsInShoppingCartEntity> shoppingCartEntityList = shoppingCartEntity.getGoodsInShoppingCarts();
        final DataEntity.ShoppingCartEntity.GoodsInShoppingCartEntity goodsInShoppingCartEntity = shoppingCartEntityList.get(childPosition);
        if (childPosition == shoppingCartEntity.getGoodsInShoppingCarts().size() - 1) {
            childHolder.priceAndConfirView.setVisibility(View.VISIBLE);
        } else {
            childHolder.priceAndConfirView.setVisibility(View.GONE);
        }
        childHolder.shoppingCartCountEditText.setText(goodsInShoppingCartEntity.getChildNum());
        childHolder.editShoppingCartCountEditText.setText(goodsInShoppingCartEntity.getChildNum());
        final String price = goodsInShoppingCartEntity.getChildPrice();
        childHolder.shoppingCartUnitPrice.setText("¥ " + price);
        childHolder.shoppingCartChildTitle.setText(goodsInShoppingCartEntity.getChildName());
        Glide.with(context).load(goodsInShoppingCartEntity.getChildPic())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)//是将图片原尺寸缓存到本地,解决Glide压缩图片偏绿问题
                .into(childHolder.imageView);

        childHolder.addTextView.setClickable(true);
        childHolder.subTextView.setClickable(true);
        childHolder.editAddTextView.setClickable(true);
        childHolder.editSubTextView.setClickable(true);
        childHolder.shoppingCartChildCheckBox.setClickable(true);
        childHolder.numberLayout.setVisibility(View.VISIBLE);

        childHolder.shoppingCartChildCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (goodsInShoppingCartEntity.isCheck()) {
                    goodsInShoppingCartEntity.setIsCheck(false);
                } else {
                    goodsInShoppingCartEntity.setIsCheck(true);
                }
                int chekcCount = 0;
                for (DataEntity.ShoppingCartEntity.GoodsInShoppingCartEntity cartEntity : shoppingCartEntityList) {
                    boolean b = cartEntity.isCheck();
                    if (b) {
                        chekcCount++;
                    }
                }
                if (chekcCount == shoppingCartEntityList.size()) {
                    shoppingCartEntity.setCheck(true);
                } else {
                    shoppingCartEntity.setCheck(false);
                }
                notifyDataSetChanged();
            }
        });
        childHolder.shoppingCartChildCheckBox.setChecked(goodsInShoppingCartEntity.isCheck());
        childHolder.addTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = childHolder.shoppingCartCountEditText.getText().toString().trim();
                int count = Integer.parseInt(s) + 1;
                goodsInShoppingCartEntity.setChildNum(count+"");
                notifyDataSetChanged();

            }
        });
        childHolder.editAddTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = childHolder.editShoppingCartCountEditText.getText().toString().trim();
                int count = Integer.parseInt(s) + 1;
                goodsInShoppingCartEntity.setChildNum(count+"");
                notifyDataSetChanged();
            }
        });
        childHolder.subTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = childHolder.shoppingCartCountEditText.getText().toString().trim();
                int count = Integer.parseInt(s) - 1;
                if (count > 0) {
                    goodsInShoppingCartEntity.setChildNum(count+"");
                    notifyDataSetChanged();
                }
            }
        });
        childHolder.editSubTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = childHolder.editShoppingCartCountEditText.getText().toString().trim();
                int count = Integer.parseInt(s) - 1;
                if (count > 0) {
                    goodsInShoppingCartEntity.setChildNum(count+"");
                    notifyDataSetChanged();
                }
            }
        });
        if (shoppingCartEntity.isEdit()) {
            childHolder.editView.setVisibility(View.VISIBLE);
            childHolder.deleteView.setVisibility(View.VISIBLE);
            childHolder.editAddSubView.setVisibility(View.VISIBLE);
            childHolder.numberLayout.setVisibility(View.GONE);
            childHolder.shoppingCartChildTitle.setVisibility(View.GONE);
            childHolder.shoppingCartUnitPrice.setVisibility(View.GONE);

        } else {
            childHolder.editView.setVisibility(View.GONE);
            childHolder.deleteView.setVisibility(View.GONE);
            childHolder.shoppingCartChildTitle.setVisibility(View.VISIBLE);
            childHolder.numberLayout.setVisibility(View.VISIBLE);
            childHolder.shoppingCartUnitPrice.setVisibility(View.VISIBLE);
        }


        childHolder.confirmTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //结算请求
            }
        });
        double totalPrice = getTotalPrice(shoppingCartEntity.getGoodsInShoppingCarts());

            childHolder.shoppingCartPriceTextView.setText("¥ "+totalPrice);
        childHolder.deleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoppingCartEntityList.remove(childPosition);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class GroupHolder {
        public CheckBox shoppingCartParentCheckBox;

        public CheckBox noShoppingCartParentCheckBox;

        public TextView merchantNameTextView;

        public TextView editView;

    }

    class ChildHolder {
        View editView;
        CheckBox shoppingCartChildCheckBox;
        View addTextView;
        View subTextView;
        EditText shoppingCartCountEditText;
        TextView shoppingCartChildTitle;
        TextView shoppingCartUnitPrice;
        ImageView imageView;
        View deleteView;
        View editAddTextView;
        View editSubTextView;
        EditText editShoppingCartCountEditText;
        TextView shoppingCartPriceTextView;
        TextView confirmTextView;
        View priceAndConfirView;
        View numberLayout;
        View editAddSubView;
    }

    private double getTotalPrice(List<DataEntity.ShoppingCartEntity.GoodsInShoppingCartEntity> list) {
        double total = 0d;
        for (int m = 0; m < list.size(); m++) {
            DataEntity.ShoppingCartEntity.GoodsInShoppingCartEntity entity = list.get(m);
            if (entity.isCheck()) {
                String num = entity.getChildNum();
                String price = entity.getChildPrice();
                if (!TextUtils.isEmpty(num) && !TextUtils.isEmpty(price)) {
                    int numInt = Integer.parseInt(num);
                    double priceFloat = Double.parseDouble(price);
                    total = Arith.add(total, Arith.mul(numInt, priceFloat));
                }
            }

        }
        return total;
    }
}
