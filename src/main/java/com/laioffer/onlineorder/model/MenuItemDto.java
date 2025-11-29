package com.laioffer.onlineorder.model;


import com.laioffer.onlineorder.entity.MenuItemEntity;


public record MenuItemDto(
        Long id,
        String name,
        String description,
        Double price,
        String imageUrl
        // Notice we have removed the restaurantId field. This is because each RestaurantDto already has an id when transferred.
        // Single source of truth and single responsibility.
) {



    public MenuItemDto(MenuItemEntity entity) {
        this(entity.id(), entity.name(), entity.description(), entity.price(), entity.imageUrl());
    }
}

