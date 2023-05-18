using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Nl2.Source.GamePlay
{
    public class World
    {
        public Hero hero;
        public World()
        {
            hero = new Hero("Hero2", new Vector2(350, 300), new Vector2(120, 110));
        }
        public virtual void UpDate()
        {
            hero.UpDate();
        }
        public virtual void Draw()
        {
            hero.Draw();
        }
    }
}
