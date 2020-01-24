package com.wc.heady.model.database.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ParentChildCategoryMapping")
public class ParentChildCategoryMapping {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "child_cat_id")
    private int childId;
    @ColumnInfo(name = "parent_cat_id")
    private int parentId;

    public ParentChildCategoryMapping(int childId, int parentId)
    {
        this.childId = childId;
        this.parentId = parentId;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getChildId()
    {
        return childId;
    }

    public void setChildId(int childId)
    {
        this.childId = childId;
    }

    public int getParentId()
    {
        return parentId;
    }

    public void setParentId(int parentId)
    {
        this.parentId = parentId;
    }
}
