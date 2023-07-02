package com.example.gig_hunt.model.repository;

import com.example.gig_hunt.model.entity.Master;
import com.example.gig_hunt.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u.user_id, u.nickname, u.role_id, u.town_id, u.category_id," +
            "u.is_busy, u.maximum, u.address, u.e_mail," +
            "u.postal_code, u.phone_number, u.watsapp_number," +
            "u.first_name, u.second_name FROM user u "
            + "INNER JOIN category c ON u.category_id = c.category_id WHERE "
            + "u.category_id = :category_id AND c.available_online = true", nativeQuery = true)
    List<Master> findMastersInCategory(@Param("category_id") Long categoryId);

    @Query(value = "SELECT u.user_id, u.nickname, u.role_id, u.town_id, u.category_id," +
            "u.is_busy, u.maximum, u.address, u.e_mail,"  +
            "u.postal_code, u.phone_number, u.watsapp_number," +
            "u.first_name, u.second_name FROM user u "
            + "INNER JOIN category c ON u.category_id = c.category_id "
            + "INNER JOIN town t ON u.town_id = t.town_id WHERE u.category_id = :category_id AND "
            + "u.town_id = :town_id AND c.available_online = false", nativeQuery = true)
    List<Master> findMastersInCategoryAndFromTown(@Param("category_id") Long categoryId, @Param("town_id") Long townId);

    @Query(value = "SELECT SUM(g.price * o.quantity) FROM order_details o INNER JOIN goods g "
            + "ON o.goods_id = g.goods_id WHERE g.master_id = :master_id GROUP BY g.master_id", nativeQuery = true)
    Double countEarnedAmount(@Param("master_id") Long masterId);

}
