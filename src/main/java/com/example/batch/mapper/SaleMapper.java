package com.example.batch.mapper;

import com.example.batch.entity.Sale;
import java.util.List;

public interface SaleMapper {
    void insertSale(Sale sale);
    void batchInsertSales(List<Sale> sales);
    Sale getSaleById(Long id);
    void updateSale(Sale sale);
    void batchUpdateSales(List<Sale> sales);
    void deleteSale(Long id);
    void batchDeleteSales(List<Long> ids);
}
