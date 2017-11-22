/**
 * MIT License
 *
 * Copyright (c) 2017 biologyiswell
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in * all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.github.biologyiswell.bedwars.arena.arenateam.solo;

import com.github.biologyiswell.bedwars.account.Account;
import com.github.biologyiswell.bedwars.arena.arenateam.ArenaTeam;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by biologyiswell on 07/10/2017.
 */
public class ArenaTeamSolo extends ArenaTeam
{
    /**
     * Account,
     * This represents the player from arena arenateam
     */
    @Setter
    @Getter
    private Account account;

    /**
     * Add Account,
     * This method add the account to the arenateam
     */
    @Override
    public void addAccount(Account account)
    {
        this.account = account;
    }

    /**
     * Remove Account,
     * This metho remove the account from the arena team
     */
    @Override
    public void removeAccount(Account account)
    {
        this.account = null;
    }
}
