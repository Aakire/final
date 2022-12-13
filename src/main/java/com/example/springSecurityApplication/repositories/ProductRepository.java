package com.example.springSecurityApplication.repositories;


import com.example.springSecurityApplication.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    // Находим продукты по части наименования без учета регистра
    List<Product> findByTitleContainingIgnoreCase(String name);


    //БЕЗ КАТЕГОРИИ!!!!! + ДИАПАЗОН
    // Поиск по наименованию, фильтрация по диапазону цены, сортировка по возрастанию цены
    @Query(value = "select * from product where ((lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') or (lower(title) LIKE '%?1')) and (price >= ?2 and price <= ?3) order by  price", nativeQuery = true)
    List<Product> findByTitlePriceAsc(String title, float ot, float Do);

    // Поиск по наименованию, фильтрация по диапазону цены, сортировка по убыванию цены
    @Query(value = "select * from product where ((lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') or (lower(title) LIKE '%?1')) and (price >= ?2 and price <= ?3) order by  price desc ", nativeQuery = true)
    List<Product> findByTitlePriceDesc(String title, float ot, float Do);



    //КАТЕГОРИЯ + ДИАПАЗОН!!!!
    @Query(value = "select * from product where category_id=?4 and ((lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') or (lower(title) LIKE '%?1')) and (price >= ?2 and price <= ?3)  order by  price", nativeQuery = true)
    List<Product> findByTitleAndCategoryPriceAsc(String title, float ot, float Do, int category);
    @Query(value = "select * from product where category_id=?4 and ((lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') or (lower(title) LIKE '%?1')) and (price >= ?2 and price <= ?3) order by  price desc ", nativeQuery = true)
    List<Product> findByTitleAndCategoryPriceDesc(String title, float ot, float Do, int category);


    //КАТЕГОРИЯ БЕЗ ДИАПАЗОНА!!!!!
    @Query(value = "select * from product where category_id=?2 and ((lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') or (lower(title) LIKE '%?1')) order by  price", nativeQuery = true)
    List<Product> findByTitleAndCategoryAsc(String title, int category);

    @Query(value = "select * from product where category_id=?2 and ((lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') or (lower(title) LIKE '%?1')) order by  price desc ", nativeQuery = true)
    List<Product> findByTitleAndCategoryDesc(String title, int category);


    //!!!ПО Возврастанию и убыванию
    @Query(value = "select * from product where  ((lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') or (lower(title) LIKE '%?1')) order by  price", nativeQuery = true)
    List<Product> findByTitleAsc(String title);
    @Query(value = "select * from product where ((lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') or (lower(title) LIKE '%?1')) order by  price desc ", nativeQuery = true)
    List<Product> findByTitleDesc(String title);


    // По категории
    @Query(value = "select * from product where category_id=?1", nativeQuery = true)
    List<Product> findByCategory(int category);

    // Метод сортировки цены
    @Query(value = "select * from product where ((lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') or (lower(title) LIKE '%?1')) and (price >= ?2 and price <= ?3)", nativeQuery = true)
    List<Product> findByTitleAndPriceGreaterThanEqualAndPriceLessThanEqual(String title, float ot, float Do);


    // Метод сортировка цены по возрастанию
    @Query(value = "select * from product order by price asc", nativeQuery = true)
    List<Product> OrderByPriceAsc(String name);

    // Метод сортировка цены по убыванию
    @Query(value = "select * from product order by price desc", nativeQuery = true)
    List<Product> OrderByPriceDesc(String name);
}

