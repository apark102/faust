package faust.powers;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import faust.BasePower;

import static faust.BasicMod.makeID;

public class BurnPower extends BasePower {
    public static final String POWER_ID = makeID("Burn");
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = true;

    public BurnPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void updateDescription() {
        this.description = "At the end of this creature's turn, take " + amount + " damage and remove Burn.";
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        // Check if this is the end of the turn for the creature who has Burn
        if (owner.isPlayer == isPlayer) {
            // Deal damage equal to Burn amount (HP loss)
            AbstractDungeon.actionManager.addToBottom(new DamageAction(
                    owner,
                    new DamageInfo(null, amount, DamageInfo.DamageType.HP_LOSS),
                    AttackEffect.FIRE
            ));

            // Remove the Burn debuff
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, POWER_ID));
        }
    }
}
