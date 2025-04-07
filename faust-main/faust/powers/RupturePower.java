package faust.powers;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import faust.BasePower;

import static faust.BasicMod.makeID;

public class RupturePower extends BasePower {
    public static final String POWER_ID = makeID("Rupture");
    private static final AbstractPower.PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public RupturePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void updateDescription() {
        this.description = "When this enemy takes damage from an Attack, consume all Rupture. "
                + "It loses 1 Dexterity per 2 Rupture (rounded down) and takes that much damage.";
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner != null && info.type == DamageInfo.DamageType.NORMAL && this.amount > 0) {
            int ruptureStacks = this.amount;

            // Deal HP loss equal to Rupture amount
            AbstractDungeon.actionManager.addToBottom(new DamageAction(
                    owner,
                    new DamageInfo(AbstractDungeon.player, ruptureStacks, DamageInfo.DamageType.HP_LOSS),
                    AbstractGameAction.AttackEffect.BLUNT_LIGHT
            ));

            // Dexterity reduction: 1 for every 2 rupture stacks (rounded down)
            int dexLoss = ruptureStacks / 2;
            if (dexLoss > 0) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
                        owner,
                        AbstractDungeon.player,
                        new DexterityPower(owner, -dexLoss),
                        -dexLoss
                ));
            }

            // Remove all Rupture stacks
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
        }

        return damageAmount;
    }
}
