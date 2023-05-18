using System;
using System.Collections.Generic;
using System.Linq;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Audio;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;
using Microsoft.Xna.Framework.Input.Touch;
using Microsoft.Xna.Framework.Media;

namespace Nl2
{
    public class Hero : Basic2d
    {
        public Hero(string Path, Vector2 pos, Vector2 dimas) : base(Path, pos, dimas)
        {

        }
        public override void UpDate()
        {
            if (Global.keyboard.GetPress("A"))
            {
                Pos = new Vector2(Pos.X - 2, Pos.Y);
            }
            if (Global.keyboard.GetPress("D"))
            {
                Pos = new Vector2(Pos.X + 2, Pos.Y);
            }
            if (Global.keyboard.GetPress("W"))
            {
                Pos = new Vector2(Pos.X, Pos.Y - 2);
            }
            if (Global.keyboard.GetPress("S"))
            {
                Pos = new Vector2(Pos.X, Pos.Y + 2);
            }


            base.UpDate();
        }
        public override void Draw()
        {
            base.Draw();
        }

    }
}
