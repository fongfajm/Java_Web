package com.laptrinhweb.demo.services.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.laptrinhweb.demo.models.Category;
import com.laptrinhweb.demo.repositories.CategoryRepository;
import com.laptrinhweb.demo.services.CategoryServices;

@Service
public class CategoryServicesImpl implements CategoryServices {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        // TODO Auto-generated method stub
        return this.categoryRepository.findAll();
    }

    @Override
    public Boolean create(Category category) {
        try {
            this.categoryRepository.save(category);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return false;
        // TODO Auto-generated method stub
    }

    @Override
    public Category findById(Integer id) {
        // TODO Auto-generated method stub
        return this.categoryRepository.findById(id).get();
    }

    @Override
    public Boolean update(Category category) {
        // TODO Auto-generated method stub
        try {
            this.categoryRepository.save(category);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean delete(Integer id) {
        // TODO Auto-generated method stub
        try {
            this.categoryRepository.delete(findById(id));
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Category> searchCategory(String keyword) {
        // TODO Auto-generated method stub
        return this.categoryRepository.searchCategory(keyword);
    }

    @Override
    public Page<Category> getAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        return this.categoryRepository.findAll(pageable);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page<Category> searchCategory(String keyword, Integer pageNo) {
        // TODO Auto-generated method stub
        @SuppressWarnings("rawtypes")
        List list = this.searchCategory(keyword);
        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        Integer start = (int) pageable.getOffset();
        Integer end = (int) ((pageable.getOffset() + pageable.getPageSize()) > list.size() ? list.size()
                : pageable.getOffset() + pageable.getPageSize());
        list = list.subList(start, end);
        return new PageImpl<>(list, pageable, this.searchCategory(keyword).size());
    }

}
