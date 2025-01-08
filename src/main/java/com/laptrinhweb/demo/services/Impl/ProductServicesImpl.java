package com.laptrinhweb.demo.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.laptrinhweb.demo.models.Product;
import com.laptrinhweb.demo.repositories.ProductRepository;
import com.laptrinhweb.demo.services.ProductServices;

@Service
public class ProductServicesImpl implements ProductServices {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Boolean create(Product product) {
        try {
            this.productRepository.save(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Product findById(Integer id) {
        return this.productRepository.findById(id).get();
    }

    @Override
    public Boolean update(Product product) {
        try {
            this.productRepository.save(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean delete(Integer id) {
        try {
            this.productRepository.delete(findById(id));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Product getProductById(Integer id) {
        // Tìm sản phẩm dựa trên ID, nếu không tìm thấy, ném ngoại lệ
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
    }

    @Override
    public List<Product> searchProduct(String keyword) {
        return this.productRepository.searchProduct(keyword);
    }

    @Override
    public Page<Product> getAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 2);
        return this.productRepository.findAll(pageable);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<Product> searchProduct(String keyword, Integer pageNo) {
        @SuppressWarnings("rawtypes")
        List list = this.searchProduct(keyword);
        Pageable pageable = PageRequest.of(pageNo - 1, 2);
        Integer start = (int) pageable.getOffset();
        Integer end = (int) ((pageable.getOffset() + pageable.getPageSize()) > list.size() ? list.size()
                : pageable.getOffset() + pageable.getPageSize());
        list = list.subList(start, end);
        return new PageImpl<>(list, pageable, this.searchProduct(keyword).size());
    }

}
