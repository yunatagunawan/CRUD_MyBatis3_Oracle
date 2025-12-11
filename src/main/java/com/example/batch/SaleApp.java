package com.example.batch;

import com.example.batch.entity.Sale;
import com.example.batch.mapper.SaleMapper;
import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaleApp {

    @Bean
    public static void main(String[] args) throws Exception {
        // 1. Load MyBatis configuration
        String resource = "mybatis-config.xml";
        InputStream inputStream;
        SqlSessionFactory sqlSessionFactory = null;
        try {
            System.out.println("MyBatis configuration start parsing.....");
            inputStream = Resources.getResourceAsStream(resource);
            System.out.println("Create SQLSessionFactoryBuilder.....");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            System.out.println("MyBatis configuration loaded successfully!");
            // You can now use sqlSessionFactory to get SqlSessions and interact with the database
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 2. Open session
        try (SqlSession session = sqlSessionFactory.openSession(true)) { // true = auto-commit
            SaleMapper saleMapper = session.getMapper(SaleMapper.class);

            // --- CREATE single sale ---
            Sale sale1 = new Sale();
            sale1.setProductName("Laptop");
            sale1.setQuantity(2);
            sale1.setPrice(null);
            sale1.setSaleDate(null);
            saleMapper.insertSale(sale1);
            System.out.println("Inserted Sale ID: " + sale1.getId());

            // --- BATCH INSERT ---
            List<Sale> batchSales = new ArrayList<>();
            for (int i = 1; i <= 3; i++) {
                Sale s = new Sale();
                s.setProductName("Product " + i);
                s.setQuantity(i * 5);
                s.setPrice(null);
                s.setSaleDate(null);
                batchSales.add(s);
            }
            saleMapper.batchInsertSales(batchSales);
            System.out.println("Batch inserted " + batchSales.size() + " sales.");

            // --- READ ---
            Sale fetched = saleMapper.getSaleById(sale1.getId());
            System.out.println("Fetched Sale: " + fetched);

            // --- UPDATE ---
            fetched.setQuantity(fetched.getQuantity() + 1);
            saleMapper.updateSale(fetched);
            System.out.println("Updated Sale ID: " + fetched.getId());

            // --- BATCH UPDATE ---
            for (Sale s : batchSales) {
                s.setQuantity(s.getQuantity() + 10);
            }
            saleMapper.batchUpdateSales(batchSales);
            System.out.println("Batch updated sales.");

            // --- DELETE ---
            saleMapper.deleteSale(fetched.getId());
            System.out.println("Deleted Sale ID: " + fetched.getId());

            // --- BATCH DELETE ---
            List<Long> idsToDelete = new ArrayList<>();
            for (Sale s : batchSales) {
                idsToDelete.add(s.getId());
            }
            saleMapper.batchDeleteSales(idsToDelete);
            System.out.println("Batch deleted sales.");
        }

        System.out.println("All operations completed successfully!");
    }
}
