/*
 * GNU LESSER GENERAL PUBLIC LICENSE
 *                       Version 3, 29 June 2007
 *
 * Copyright (C) 2007 Free Software Foundation, Inc. <http://fsf.org/>
 * Everyone is permitted to copy and distribute verbatim copies
 * of this license document, but changing it is not allowed.
 *
 * You can view LICENCE file for details. 
 *
 * @author The Dragonet Team
 */
package org.dragonet.proxy.network;

import com.github.steveice10.mc.protocol.packet.ingame.server.ServerPlayerListEntryPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.*;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.player.ServerPlayerPositionRotationPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.spawn.ServerSpawnObjectPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.spawn.ServerSpawnPlayerPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.window.ServerOpenWindowPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.window.ServerSetSlotPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.window.ServerWindowItemsPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.*;
import com.github.steveice10.packetlib.packet.Packet;
import org.dragonet.proxy.network.translator.IPCPacketTranslator;
import java.util.HashMap;
import java.util.Map;
import org.dragonet.proxy.network.translator.IPEPacketTranslator;
import org.dragonet.proxy.network.translator.pc.*;
import org.dragonet.proxy.network.translator.pe.*;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerChatPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerJoinGamePacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.player.ServerPlayerHealthPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.spawn.ServerSpawnMobPacket;
import org.dragonet.proxy.protocol.PEPacket;
import org.dragonet.proxy.protocol.packets.*;

public final class PacketTranslatorRegister {

    private static final Map<Class<? extends Packet>, IPCPacketTranslator<? extends Packet>> PC_TO_PE_TRANSLATOR = new HashMap<>();
    private static final Map<Class<? extends PEPacket>, IPEPacketTranslator<? extends PEPacket>> PE_TO_PC_TRANSLATOR = new HashMap<>();

    /**
     * PC to PE
     */
    static {
        // Login phase
        PC_TO_PE_TRANSLATOR.put(ServerJoinGamePacket.class, new PCJoinGamePacketTranslator());

        // Settings && Weather
        PC_TO_PE_TRANSLATOR.put(ServerNotifyClientPacket.class, new PCNotifyClientPacketTranslator());

        // Chat
        PC_TO_PE_TRANSLATOR.put(ServerChatPacket.class, new PCChatPacketTranslator());

        // Map
        PC_TO_PE_TRANSLATOR.put(ServerChunkDataPacket.class, new PCMultiChunkDataPacketTranslator());

        PC_TO_PE_TRANSLATOR.put(ServerUpdateTimePacket.class, new PCUpdateTimePacketTranslator());
        PC_TO_PE_TRANSLATOR.put(ServerBlockChangePacket.class, new PCBlockChangePacketTranslator());
        PC_TO_PE_TRANSLATOR.put(ServerMultiBlockChangePacket.class, new PCMultiBlockChangePacketTranslator());
        // PC_TO_PE_TRANSLATOR.put(ServerUpdateSignPacket.class, new
        // PCUpdateSignPacketTranslator());
        PC_TO_PE_TRANSLATOR.put(ServerPlaySoundPacket.class, new PCPlaySoundPacketTranslator());
        //
        // // Entity
        PC_TO_PE_TRANSLATOR.put(ServerPlayerPositionRotationPacket.class, new PCPlayerPositionRotationPacketTranslator());
        PC_TO_PE_TRANSLATOR.put(ServerSpawnMobPacket.class, new PCSpawnMobPacketTranslator());
        PC_TO_PE_TRANSLATOR.put(ServerPlayerListEntryPacket.class, new PCPlayerListItemPacketTranslator());
        PC_TO_PE_TRANSLATOR.put(ServerSpawnPlayerPacket.class, new PCSpawnPlayerPacketTranslator());
        PC_TO_PE_TRANSLATOR.put(ServerSpawnObjectPacket.class, new PCSpawnObjectPacketTranslator());
        PC_TO_PE_TRANSLATOR.put(ServerEntityMetadataPacket.class, new PCEntityMetadataPacketTranslator());
        PC_TO_PE_TRANSLATOR.put(ServerEntityDestroyPacket.class, new PCDestroyEntitiesPacketTranslator());
        PC_TO_PE_TRANSLATOR.put(ServerEntityPositionRotationPacket.class, new PCEntityPositionRotationPacketTranslator());
        PC_TO_PE_TRANSLATOR.put(ServerEntityPositionPacket.class, new PCEntityPositionPacketTranslator());
        PC_TO_PE_TRANSLATOR.put(ServerEntityRotationPacket.class, new PCEntityRotationPacketTranslator());
        PC_TO_PE_TRANSLATOR.put(ServerEntityVelocityPacket.class, new PCEntityVelocityPacketTranslator());
        PC_TO_PE_TRANSLATOR.put(ServerEntityEffectPacket.class, new PCEntityEffectPacketTranslator());
        PC_TO_PE_TRANSLATOR.put(ServerEntityRemoveEffectPacket.class, new PCEntityRemoveEffectPacketTranslator());
        PC_TO_PE_TRANSLATOR.put(ServerPlayerHealthPacket.class, new PCUpdateHealthPacketTranslator());
        PC_TO_PE_TRANSLATOR.put(ServerEntityAnimationPacket.class, new PCAnimationPacketTranslator());
        PC_TO_PE_TRANSLATOR.put(ServerEntitySetPassengersPacket.class, new PCEntitySetPassengerPacketTranslator());
        //
        // //Inventory
        PC_TO_PE_TRANSLATOR.put(ServerOpenWindowPacket.class, new PCOpenWindowPacketTranslator());
        PC_TO_PE_TRANSLATOR.put(ServerWindowItemsPacket.class, new PCWindowItemsTranslator());
        PC_TO_PE_TRANSLATOR.put(ServerSetSlotPacket.class, new PCSetSlotPacketTranslator());

    }

    /**
     * PE to PC
     */
    static {
        // Map
        PE_TO_PC_TRANSLATOR.put(LevelSoundEventPacket.class, new PESoundEventPacketTranslator());

        // Chat
        PE_TO_PC_TRANSLATOR.put(TextPacket.class, new PEChatPacketTranslator());
        PE_TO_PC_TRANSLATOR.put(CommandRequestPacket.class, new PECommandRequestPacketTranslator());

        // Entity
        // PE_TO_PC_TRANSLATOR.put(UseItem.class, new PEUseItemPacketTranslator());
        PE_TO_PC_TRANSLATOR.put(MovePlayerPacket.class, new PEMovePlayerPacketTranslator());
        PE_TO_PC_TRANSLATOR.put(PlayerActionPacket.class, new PEPlayerActionPacketTranslator());
        PE_TO_PC_TRANSLATOR.put(InteractPacket.class, new PEInteractPacketTranslator());
        PE_TO_PC_TRANSLATOR.put(AdventureSettingsPacket.class, new PEAdventureSettingsPacketTranslator());
        PE_TO_PC_TRANSLATOR.put(PlayerInputPacket.class, new PEPlayerInputPacketTranslator());

        // Inventory
        PE_TO_PC_TRANSLATOR.put(ContainerClosePacket.class, new PEWindowClosePacketTranslator());
        PE_TO_PC_TRANSLATOR.put(MobEquipmentPacket.class, new PEPlayerEquipmentPacketTranslator());
        PE_TO_PC_TRANSLATOR.put(InventoryTransactionPacket.class, new PEInventoryTransactionPacketTranslator());
        PE_TO_PC_TRANSLATOR.put(BlockPickRequestPacket.class, new PEBlockPickRequestPacketTranslator());
    }

    public static PEPacket[] translateToPE(UpstreamSession session, Packet packet) {
        if (packet == null) {
            return null;
        }
        IPCPacketTranslator<Packet> target = (IPCPacketTranslator<Packet>) PC_TO_PE_TRANSLATOR.get(packet.getClass());
        if (target == null) {
            return null;
        }
        try {
            return target.translate(session, packet);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Packet[] translateToPC(UpstreamSession session, PEPacket packet) {
        if (packet == null) {
            return null;
        }
        IPEPacketTranslator<PEPacket> target = (IPEPacketTranslator<PEPacket>) PE_TO_PC_TRANSLATOR
                .get(packet.getClass());
        if (target == null) {
            return null;
        }
        try {
            return target.translate(session, packet);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
