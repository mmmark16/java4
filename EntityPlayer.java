package com.company;

import java.io.Serializable;
import java.util.Date;

public class EntityPlayer extends Entity implements Serializable {
    String nickname;
    private double exp;
    public void update() {
        if (GameServer.getInstance().getServerTicks()%2 == 0 && this.health < this.maxHealth) {
            this.health++;
        }
    }

    public EntityPlayer(String title, double posX, double posZ, int maxHealth, int health, int attackDamage, World world, Date date_created, String nickname, double exp) {
        super(title, posX, posZ, false, maxHealth, health, attackDamage, world, date_created);
        this.nickname = nickname;
    }

    public double getExp() {
        return exp;
    }

    public void setExp(double exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "EntityPlayer{" +
                "nickname='" + nickname + '\'' +
                ", maxHealth=" + maxHealth +
                ", health=" + health +
                '}';
    }
}