package com.example.freestyle_training.kurochi;

import java.io.Serializable;
import java.util.List;

public class tagInput implements Serializable  {
    private String tag;

    // ゲッタ-セッター
    public String getTag() {
        return tag;
    }

    public void setTag(String value) {
        tag = value;
    }
    
    private String selectedItem;

	public String getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(String selectedItem) {
		this.selectedItem = selectedItem;
	}
	private String[] selectedItems;

	public String[] getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(String[] selectedItems) {
		this.selectedItems = selectedItems;
	}

}
