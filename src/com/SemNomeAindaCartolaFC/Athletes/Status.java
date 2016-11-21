package com.SemNomeAindaCartolaFC.Athletes;

import com.SemNomeAindaCartolaFC.DB.DataIdentifiable;

public class Status extends DataIdentifiable{

    public Integer id;
    public String name;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
