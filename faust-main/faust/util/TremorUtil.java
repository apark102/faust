package faust.util;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import faust.powers.TremorPower;

public class TremorUtil {

    public static void doTremorBurst(AbstractPlayer player, AbstractMonster monster) {
        if (monster.hasPower(TremorPower.POWER_ID)) {
            int tremor = monster.getPower(TremorPower.POWER_ID).amount;

            // Deal 2x Tremor as damage
            int damage = tremor * 2;
            addToBot(new DamageAction(
                    monster,
                    new DamageInfo(player, damage, DamageInfo.DamageType.NORMAL),
                    com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.BLUNT_HEAVY
            ));

            // Apply 2 Weak and 2 Vulnerable
            addToBot(new ApplyPowerAction(monster, player,
                    new com.megacrit.cardcrawl.powers.WeakPower(monster, 2, false)));

            addToBot(new ApplyPowerAction(monster, player,
                    new com.megacrit.cardcrawl.powers.VulnerablePower(monster, 2, false)));

            // Remove Tremor
            addToBot(new RemoveSpecificPowerAction(monster, player, TremorPower.POWER_ID));
        }
    }

    private static void addToBot(com.megacrit.cardcrawl.actions.AbstractGameAction action) {
        com.megacrit.cardcrawl.dungeons.AbstractDungeon.actionManager.addToBottom(action);
    }
}
