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
package com.github.biologyiswell.bedwars.arena.arenateam.doublee;

import com.github.biologyiswell.bedwars.account.Account;
import com.github.biologyiswell.bedwars.arena.arenateam.ArenaTeam;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by biologyiswell on 07/10/2017.
 */
public class ArenaTeamDouble extends ArenaTeam
{
    /** Accounts list, this represents the accounts from the arena arenateam double */
    @Getter
    private List<Account> accounts;

    /**
     * ArenaTeamDouble, constructor
     */
    public ArenaTeamDouble()
    {
        this.accounts = new ArrayList<>();
    }

    /**
     * Add Account,
     * This method add the account to the arenateam
     */
    @Override
    public void addAccount(Account account)
    {
        // Check if the players contains in the account to avoid to the player not occupies a one space more
        if (!this.accounts.contains(account))
        {
            this.accounts.add(account);
        }
    }

    /**
     * Remove Account,
     * This method remove the account from the arena team
     */
    @Override
    public void removeAccount(Account account)
    {
        if (this.accounts.contains(account))
        {
            this.accounts.remove(account);
        }
    }
}
