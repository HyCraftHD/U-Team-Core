package info.u_team.u_team_test.init;

import info.u_team.u_team_test.TestMod;
import info.u_team.u_team_test.entity.BetterEnderPearlEntity;
import net.minecraft.entity.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.*;

public class TestEntityTypes {
	
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, TestMod.MODID);
	
	public static final RegistryObject<EntityType<BetterEnderPearlEntity>> BETTER_ENDERPEARL = ENTITY_TYPES.register("better_enderpearl", () -> EntityType.Builder.<BetterEnderPearlEntity> create(BetterEnderPearlEntity::new, EntityClassification.MISC).size(0.25F, 0.25F).setTrackingRange(128).setUpdateInterval(20).setShouldReceiveVelocityUpdates(true).disableSerialization().build(null));
	
	public static void register(IEventBus bus) {
		ENTITY_TYPES.register(bus);
	}
}
