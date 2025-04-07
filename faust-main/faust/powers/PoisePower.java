package faust.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import faust.BasePower;

import static faust.BasicMod.makeID;

public class PoisePower extends BasePower {
    public static final String POWER_ID = makeID("Poise");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private static final int MAX_POISE = 10;

    public PoisePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        if (this.amount > MAX_POISE) {
            this.amount = MAX_POISE;
        }
    }

    @Override
    public void updateDescription() {
        if (amount >= 10) {
            this.description = "At 10 Poise: Attacks deal 2x damage.";
        } else if (amount >= 5) {
            this.description = "At 5+ Poise: Attacks deal 1.5x damage (rounded down).";
        } else {
            this.description = "Gain effects at 5 and 10 Poise.";
        }
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        if (type == DamageInfo.DamageType.NORMAL && card != null && card.type == AbstractCard.CardType.ATTACK) {
            if (amount >= 10) {
                return damage * 2f;
            } else if (amount >= 5) {
                return (float)Math.floor(damage * 1.5f);
            }
        }
        return damage;
    }

    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0f;
        this.amount += stackAmount;
        if (this.amount > MAX_POISE) {
            this.amount = MAX_POISE;
        }
        updateDescription();
    }
}
