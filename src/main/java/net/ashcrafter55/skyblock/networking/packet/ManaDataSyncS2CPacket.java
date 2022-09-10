package net.ashcrafter55.skyblock.networking.packet;

import net.ashcrafter55.skyblock.client.ClientManaData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ManaDataSyncS2CPacket {
    private final int mana;
    private final int maxMana;

    public ManaDataSyncS2CPacket(int mana, int max_mana) {
        this.mana = mana;
        this.maxMana = max_mana;
    }

    public ManaDataSyncS2CPacket(FriendlyByteBuf buf) {
        this.mana = buf.readInt();
        this.maxMana = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(mana);
        buf.writeInt(maxMana);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ClientManaData.set(mana);
            ClientManaData.setMax(maxMana);
        });
        return true;
    }
}
