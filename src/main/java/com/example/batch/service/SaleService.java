package com.example.batch.service;

import com.example.batch.entity.Sale;
import com.example.batch.mapper.SaleMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class SaleService {

    private final SqlSessionFactory sqlSessionFactory;

    public SaleService(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    /** Insert single sale */
    public void addSale(Sale sale) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) { // auto-commit
            SaleMapper mapper = session.getMapper(SaleMapper.class);
            mapper.insertSale(sale);
        }
    }

    /** Batch insert sales */
    public void addSalesBatch(List<Sale> sales) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            SaleMapper mapper = session.getMapper(SaleMapper.class);
            mapper.batchInsertSales(sales);
        }
    }

    /** Get sale by id */
    public Sale getSale(Long id) {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            SaleMapper mapper = session.getMapper(SaleMapper.class);
            return mapper.getSaleById(id);
        }
    }

    /** Update single sale */
    public void updateSale(Sale sale) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            SaleMapper mapper = session.getMapper(SaleMapper.class);
            mapper.updateSale(sale);
        }
    }

    /** Batch update sales */
    public void updateSalesBatch(List<Sale> sales) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            SaleMapper mapper = session.getMapper(SaleMapper.class);
            mapper.batchUpdateSales(sales);
        }
    }

    /** Delete single sale */
    public void deleteSale(Long id) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            SaleMapper mapper = session.getMapper(SaleMapper.class);
            mapper.deleteSale(id);
        }
    }

    /** Batch delete sales */
    public void deleteSalesBatch(List<Long> ids) {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            SaleMapper mapper = session.getMapper(SaleMapper.class);
            mapper.batchDeleteSales(ids);
        }
    }
}
