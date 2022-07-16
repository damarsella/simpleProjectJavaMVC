package com.controller;

import com.DAO.DaoBarang;
import com.DAO.ImplementBarang;
import com.model.ModelBarang;
import com.model.ModelTbBarang;
import java.util.List;
import com.view.FormBarang;
import javax.swing.JOptionPane;

public class ControllerBarang{
    
    FormBarang fRame;
    ImplementBarang implmBarang;
    List<ModelBarang> lmb;

    // Constructor
    public ControllerBarang(FormBarang fRame){
        this.fRame = fRame;
        implmBarang = new DaoBarang();
        lmb = implmBarang.getAll();
    }
    
    public void reset(){
        fRame.getTxtNo().setText("");
        fRame.getTxtName().setText("");
        fRame.getTxtPrice().setText("");
        fRame.getTxtStock().setText("");
    }
    
    public void isiTable(){
        lmb = implmBarang.getAll();
        ModelTbBarang mtb = new ModelTbBarang(lmb);
        fRame.getjTable1().setModel(mtb);
    }
    
    public void isiField(int row){
        fRame.getTxtNo().setText(String.valueOf(lmb.get(row).getNo()));
        fRame.getTxtName().setText(lmb.get(row).getName());
        fRame.getTxtPrice().setText(String.valueOf(lmb.get(row).getPrice()));
        fRame.getTxtStock().setText(String.valueOf(lmb.get(row).getStock()));
    }
    
    public void insert(){
        if (!fRame.getTxtName().getText().trim().isEmpty() & !fRame.getTxtName().getText().trim().isEmpty()){
            ModelBarang mb = new ModelBarang();
            
            mb.setName(fRame.getTxtName().getText());
            mb.setPrice(Double.valueOf(fRame.getTxtPrice().getText()));
            mb.setStock(Integer.valueOf(fRame.getTxtStock().getText()));
            
            implmBarang.insert(mb);
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
        } else {
            JOptionPane.showMessageDialog(null, "Data Gagal Disimpan");
        }
    }
    
    public void delete(){
        if (!fRame.getTxtNo().getText().trim().isEmpty()) {
            int no = Integer.parseInt(fRame.getTxtNo().getText());
            
            implmBarang.delete(no);
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");            
        } else {
            JOptionPane.showMessageDialog(null, "Data Gagal Dihapus");
        }
        
    }
    
    public void update(){
        if (!fRame.getTxtNo().getText().trim().isEmpty()){
            ModelBarang mb = new ModelBarang();
            
            mb.setName(fRame.getTxtName().getText());
            mb.setPrice(Double.valueOf(fRame.getTxtPrice().getText()));
            mb.setStock(Integer.valueOf(fRame.getTxtStock().getText()));
            mb.setNo(Integer.valueOf(fRame.getTxtNo().getText()));
            
            implmBarang.update(mb);
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
        } else {
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah");
        }
        
    }
    
    public void isiTableSearchName(){
        lmb = implmBarang.getSearchName(fRame.getTxtSearch().getText());
        ModelTbBarang mtb = new ModelTbBarang(lmb);
        fRame.getjTable1().setModel(mtb);
    }
    
    public void searchName(){
        if (!fRame.getTxtSearch().getText().trim().isEmpty()){
            implmBarang.getSearchName(fRame.getTxtSearch().getText());
            isiTableSearchName();
        }else {
            JOptionPane.showMessageDialog(null, "Silahkan Masukan Nama");
        }
    }
    
}
