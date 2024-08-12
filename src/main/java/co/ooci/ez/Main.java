package co.ooci.ez;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        // 获取原始伤害
        double originalDamage = event.getDamage();

        // 取消原有事件（不会产生特效）
        event.setCancelled(true);

        if (event.getEntity() instanceof LivingEntity entity) {
            // 触发一次新的伤害事件但伤害较小，减少特效的产生
            entity.damage(originalDamage * 0.1);

            // 手动减少实体的生命值（不会产生特效）
            entity.setHealth(entity.getHealth() - originalDamage);
        }
    }
}
