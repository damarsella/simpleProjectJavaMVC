package com.DAO;

import com.model.ModelBarang;
import java.util.List;

public interface ImplementBarang 
{    
    public void insert(ModelBarang mb);
    
    public void delete(int no);
    
    public void update(ModelBarang mb);
    
    public List<ModelBarang> getAll();
    
    // Buat cari nama pada formBarang
    public List<ModelBarang> getSearchName(String name);
    
}
