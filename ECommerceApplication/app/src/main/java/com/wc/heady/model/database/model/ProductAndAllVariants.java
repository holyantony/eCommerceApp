package com.wc.heady.model.database.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class ProductAndAllVariants {

    @Embedded
    public Product product;

    @Relation(parentColumn = "id",entityColumn = "product_id" , entity = Variants.class)
    public List<Variants> variantsList;
}
