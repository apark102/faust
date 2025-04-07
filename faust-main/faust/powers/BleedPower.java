package faust.powers;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.cards.DamageInfo;
import faust.BasePower;

import static faust.BasicMod.makeID;

public class BleedPower extends BasePower {
    public static final String POWER_ID = makeID("Bleed");
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public BleedPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void onAfterCardPlayed(AbstractCard card) {
        if (card.type == AbstractCard.CardType.ATTACK && amount > 0) {
            flash();

            // Deal damage to the owner of this power (the enemy)
            AbstractDungeon.actionManager.addToBottom(new DamageAction(
                    owner,
                    new DamageInfo(null, amount, DamageInfo.DamageType.HP_LOSS),
                    AttackEffect.NONE
            ));

            // Reduce Bleed by 1
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(owner, owner, this, 1));
        }
    }

    @Override
    public void updateDescription() {
        description = "Whenever you play an Attack, this enemy takes " + amount +
                " damage and Bleed is reduced by 1.";
    }
}
