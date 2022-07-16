package com.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelTbBarang extends AbstractTableModel {
    
    List<ModelBarang> lmb;

    // Start Constructor
    public ModelTbBarang(List<ModelBarang> lmb) {
        this.lmb = lmb;
    }
    // End Constructor

    @Override
    public int getRowCount() {
        return lmb.size();
    }

    @Override
    public int getColumnCount() {
        // Sesuai column di database
        return 4;         
    }

    @Override
    public Object getValueAt(int row, int column) 
    {       
        switch(column) {
            case 0:
                return lmb.get(row).getNo();
            case 1:
                return lmb.get(row).getName();
            case 2:
                return lmb.get(row).getPrice();
            case 3:
                return lmb.get(row).getStock();
            default :
                return null;
        }
        
    }      
    

    @Override
    public String getColumnName(int column) {
        switch(column) {
            case 0:
                return "No";
            case 1:
                return "Name";
            case 2:
                return "Price";
            case 3:
                return "Stock";
            default:
                return null;
        }
       
    }
    
}