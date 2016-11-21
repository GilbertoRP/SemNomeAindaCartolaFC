package com.SemNomeAindaCartolaFC.Athletes;

import com.SemNomeAindaCartolaFC.DB.DataIdentifiable;

public class Position extends DataIdentifiable {
    public Integer id;
    public String name;
    public String abreviation;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
