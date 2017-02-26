package com.example.shoppingcart.shoppingcart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengxiaonan on 2017/2/25.
 */

public class DataEntity {
    private List<ShoppingCartEntity> shoppingCarts;

    public List<ShoppingCartEntity> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(List<ShoppingCartEntity> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    public  static class ShoppingCartEntity {
        private String parentId;
        private String parentName;
        private String parentPrice;
        private ArrayList<GoodsInShoppingCartEntity> goodsInShoppingCarts;
        private transient boolean isEdit;
        private transient boolean isCheck = true;

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getParentName() {
            return parentName;
        }

        public void setParentName(String parentName) {
            this.parentName = parentName;
        }

        public String getParentPrice() {
            return parentPrice;
        }

        public void setParentPrice(String parentPrice) {
            this.parentPrice = parentPrice;
        }

        public ArrayList<GoodsInShoppingCartEntity> getGoodsInShoppingCarts() {
            return goodsInShoppingCarts;
        }

        public void setGoodsInShoppingCarts(ArrayList<GoodsInShoppingCartEntity> goodsInShoppingCarts) {
            this.goodsInShoppingCarts = goodsInShoppingCarts;
        }

        public boolean isEdit() {
            return isEdit;
        }

        public void setEdit(boolean edit) {
            isEdit = edit;
        }

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        public static class GoodsInShoppingCartEntity {
            private String childPic;
            private String childId;
            private String childName;
            private String childPrice;
            private String childNum;
            private transient boolean isCheck = true;

            public String getChildPic() {
                return childPic;
            }

            public void setChildPic(String childPic) {
                this.childPic = childPic;
            }

            public String getChildId() {
                return childId;
            }

            public void setChildId(String childId) {
                this.childId = childId;
            }

            public String getChildName() {
                return childName;
            }

            public void setChildName(String childName) {
                this.childName = childName;
            }

            public String getChildPrice() {
                return childPrice;
            }

            public void setChildPrice(String childPrice) {
                this.childPrice = childPrice;
            }

            public String getChildNum() {
                return childNum;
            }

            public void setChildNum(String childNum) {
                this.childNum = childNum;
            }

            public boolean isCheck() {
                return isCheck;
            }

            public void setIsCheck(boolean check) {
                isCheck = check;
            }
        }

    }
}
