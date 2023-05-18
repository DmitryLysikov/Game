using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Nl2
{
    public class Basic2d
    {
        public Vector2 Pos, Dims;
        public Texture2D Texture;
        public Basic2d(string Path, Vector2 pos, Vector2 dimas)
        {
            Pos = pos;
            Dims = dimas;
            Texture = Global.content.Load<Texture2D>(Path); 
        }
        public virtual void UpDate()
        {

        }
        public virtual void Draw()
        {
            if (Texture != null)
            {
                Global.spriteBatch.Draw(Texture, new Rectangle((int)(Pos.X), (int)(Pos.Y), (int)Dims.X, (int)Dims.Y), null, Color.White, 0.0f, new Vector2(Texture.Bounds.Width/2, Texture.Bounds.Height/2), new SpriteEffects(), 0);
            } 
        }
    }
}
